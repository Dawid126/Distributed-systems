package Dispatcher;

import MonitoringStation.MonitoringStationActor;
import Dispatcher.Workers.StatusCollectorActor;
import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;


public class DispatcherActor extends AbstractBehavior<DispatcherActor.DispatcherCommand> {

    public interface DispatcherCommand {}

    public static final class RequestSatellitesDataCommand implements DispatcherActor.DispatcherCommand {
        public final int queryID;
        public final int firstSatelliteID;
        public final int range;
        public final int timeout;
        public final ActorRef<MonitoringStationActor.MonitoringStationCommand> replyTo;

        public RequestSatellitesDataCommand(int queryID,
                                          int firstSatelliteID,
                                          int range,
                                          int timeout,
                                          ActorRef<MonitoringStationActor.MonitoringStationCommand> replyTo) {
            this.queryID = queryID;
            this.firstSatelliteID = firstSatelliteID;
            this.range = range;
            this.timeout = timeout;
            this.replyTo = replyTo;
        }
    }

    public static final class StopStatusCollectorActorCommand implements DispatcherActor.DispatcherCommand {
        public final ActorRef<StatusCollectorActor.StatusCollectorCommand> statusCollectorActor;

        public StopStatusCollectorActorCommand(ActorRef<StatusCollectorActor.StatusCollectorCommand> statusCollectorActor) {
            this.statusCollectorActor = statusCollectorActor;
        }
    }

    public DispatcherActor(ActorContext<DispatcherCommand> context) {
        super(context);
    }

    public static Behavior<DispatcherActor.DispatcherCommand> create() {
        return Behaviors.setup(DispatcherActor::new);
    }

    @Override
    public Receive<DispatcherActor.DispatcherCommand> createReceive() {
        return newReceiveBuilder()
                .onMessage(DispatcherActor.RequestSatellitesDataCommand.class, this::onDispatcherRequestSatellitesDataCommand)
                .onMessage(DispatcherActor.StopStatusCollectorActorCommand.class, this::onStopStatusCollectorActorCommand)
                .build();
    }

    private Behavior<DispatcherActor.DispatcherCommand> onDispatcherRequestSatellitesDataCommand(
            DispatcherActor.RequestSatellitesDataCommand requestSatellitesDataCommand) {

        ActorRef<StatusCollectorActor.StatusCollectorCommand> statusCollectorActor = getContext().spawnAnonymous(
                StatusCollectorActor.create(
                        requestSatellitesDataCommand.queryID,
                        requestSatellitesDataCommand.firstSatelliteID,
                        requestSatellitesDataCommand.range,
                        requestSatellitesDataCommand.timeout,
                        requestSatellitesDataCommand.replyTo,
                        getContext().getSelf()
                        )
        );

        statusCollectorActor.tell(new StatusCollectorActor.SendRequestsForSatellitesDataCommand());

        return this;
    }

    private Behavior<DispatcherActor.DispatcherCommand> onStopStatusCollectorActorCommand(
            DispatcherActor.StopStatusCollectorActorCommand stopStatusCollectorActorCommand) {

        getContext().stop(stopStatusCollectorActorCommand.statusCollectorActor);
        return this;
    }

}
