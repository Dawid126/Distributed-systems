package Dispatcher.Workers;

import Dispatcher.DispatcherActor;
import MonitoringStation.MonitoringStationActor;
import Satellite.SatelliteAPI;
import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.DispatcherSelector;
import akka.actor.typed.javadsl.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletionStage;

public class StatusCollectorActor extends AbstractBehavior<StatusCollectorActor.StatusCollectorCommand> {

    public interface StatusCollectorCommand{}


    public static final class SendRequestsForSatellitesDataCommand implements StatusCollectorActor.StatusCollectorCommand {

        public SendRequestsForSatellitesDataCommand() {
        }
    }

    public static final class ReceiveSatelliteDataCommand implements StatusCollectorActor.StatusCollectorCommand {
        public final SatelliteAPI.Status status;
        public final int satelliteID;

        public ReceiveSatelliteDataCommand(SatelliteAPI.Status status, int satelliteID) {
            this.status = status;
            this.satelliteID = satelliteID;
        }
    }

    private final Map<Integer, SatelliteAPI.Status> satellitesErrorMap;
    private final int queryID;
    private final int firstSatelliteID;
    private final int range;
    private final int timeout;
    private int satellitesLeft;
    private int numberOfTimeouts;
    private final ActorRef<MonitoringStationActor.MonitoringStationCommand> replyTo;
    private final ActorRef<DispatcherActor.DispatcherCommand> dispatcher;

    public StatusCollectorActor(ActorContext<StatusCollectorCommand> context,
                                int queryID,
                                int firstSatelliteID,
                                int range,
                                int timeout,
                                ActorRef<MonitoringStationActor.MonitoringStationCommand> replyTo,
                                ActorRef<DispatcherActor.DispatcherCommand> dispatcher) {

        super(context);
        this.satellitesErrorMap = new HashMap<>();
        this.queryID = queryID;
        this.firstSatelliteID = firstSatelliteID;
        this.range = range;
        this.timeout = timeout;
        this.satellitesLeft = range;
        this.numberOfTimeouts = 0;
        this.replyTo = replyTo;
        this.dispatcher = dispatcher;
    }

    public static Behavior<StatusCollectorActor.StatusCollectorCommand> create(int queryID,
                                                                               int firstSatelliteID,
                                                                               int range,
                                                                               int timeout,
                                                                               ActorRef<MonitoringStationActor.MonitoringStationCommand> replyTo,
                                                                               ActorRef<DispatcherActor.DispatcherCommand> dispatcher) {

        return Behaviors.setup(context -> new StatusCollectorActor(context, queryID, firstSatelliteID, range, timeout, replyTo, dispatcher));
    }

    @Override
    public Receive<StatusCollectorActor.StatusCollectorCommand> createReceive() {
        return newReceiveBuilder()
                .onMessage(SendRequestsForSatellitesDataCommand.class, this::onStatusCollectorSendRequestsForSatellitesDataCommand)
                .build();
    }

    private Behavior<StatusCollectorActor.StatusCollectorCommand> onStatusCollectorSendRequestsForSatellitesDataCommand(
            SendRequestsForSatellitesDataCommand statusCollectorSendRequestsForSatellitesDataCommand) {

        for(int i = firstSatelliteID; i < firstSatelliteID + range; i++)
        {
            CompletionStage<ReceiveSatelliteDataCommand> result = AskPattern.ask(
                    getContext().spawn(SatelliteDataRetrievalActor.create(i),
                            "query" + queryID + "satellite" + i,
                            DispatcherSelector.fromConfig("my-dispatcher")),
                    replyTo -> new SatelliteDataRetrievalActor.RequestSatelliteDataCommand(replyTo.unsafeUpcast()),
                    Duration.ofMillis(timeout),
                    getContext().getSystem().scheduler());

            result.whenComplete(
                    (reply, failure) ->
                    {
                        satellitesLeft--;
                        if (reply != null)
                        {
                            if(reply.status!= SatelliteAPI.Status.OK) {
                                satellitesErrorMap.put(reply.satelliteID, reply.status);
                            }
                        } else {
                            numberOfTimeouts++;
                        }
                        if(satellitesLeft == 0) {
                            int correctResponsesPercentage = (range - numberOfTimeouts) * 100 / range;
                            replyTo.tell(new MonitoringStationActor.ReceiveResponseCommand(queryID,
                                    satellitesErrorMap,
                                    correctResponsesPercentage));

                            dispatcher.tell(new DispatcherActor.StopStatusCollectorActorCommand(getContext().getSelf()));

                        }
                    });
        }
        return this;
    }
}
