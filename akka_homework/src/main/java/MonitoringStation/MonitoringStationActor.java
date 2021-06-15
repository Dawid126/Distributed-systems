package MonitoringStation;

import Dispatcher.DispatcherActor;
import Satellite.SatelliteAPI;
import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.SupervisorStrategy;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

public class MonitoringStationActor extends AbstractBehavior<MonitoringStationActor.MonitoringStationCommand> {

    public interface MonitoringStationCommand {}

    public static final class RequestSatellitesDataCommand implements MonitoringStationCommand {
        public final int queryID;
        public final int firstSatelliteID;
        public final int range;
        public final int timeout;

        public RequestSatellitesDataCommand(int queryID, int firstSatelliteID, int range, int timeout) {
            this.queryID = queryID;
            this.firstSatelliteID = firstSatelliteID;
            this.range = range;
            this.timeout = timeout;
        }
    }

    public static final class ReceiveResponseCommand implements MonitoringStationCommand {
        public final int queryID;
        public final Map<Integer, SatelliteAPI.Status> satellitesErrorMap;
        public final int correctResponsesPercentage;

        public ReceiveResponseCommand(int queryID,
                                      Map<Integer, SatelliteAPI.Status> satellitesErrorMap,
                                      int correctResponsesPercentage) {

            this.queryID = queryID;
            this.satellitesErrorMap = satellitesErrorMap;
            this.correctResponsesPercentage = correctResponsesPercentage;
        }
    }

    private final String name;
    private final ActorRef<DispatcherActor.DispatcherCommand> dispatcher;
    private final Map<Integer, Long> getStatusCommandsSendTimes;

    public MonitoringStationActor(ActorContext<MonitoringStationCommand> context,
                                  String name, ActorRef<DispatcherActor.DispatcherCommand> dispatcher) {

        super(context);
        this.name = name;
        this.dispatcher = dispatcher;
        this.getStatusCommandsSendTimes = new HashMap<>();
    }

    public static Behavior<MonitoringStationActor.MonitoringStationCommand> create(String name,
                                                                                   ActorRef<DispatcherActor.DispatcherCommand> dispatcher) {

        return Behaviors.setup(context -> new MonitoringStationActor(context, name, dispatcher));
    }

    @Override
    public Receive<MonitoringStationCommand> createReceive() {
        return newReceiveBuilder()
                .onMessage(RequestSatellitesDataCommand.class, this::onMonitoringStationRequestSatellitesDataCommand)
                .onMessage(ReceiveResponseCommand.class, this::onMonitoringStationReceiveResponseCommand)
                .build();
    }

    private Behavior<MonitoringStationCommand> onMonitoringStationRequestSatellitesDataCommand(
            RequestSatellitesDataCommand monitoringStationGetStatusCommand) {

        getStatusCommandsSendTimes.put(monitoringStationGetStatusCommand.queryID, System.currentTimeMillis());

        dispatcher.tell(
                new DispatcherActor.RequestSatellitesDataCommand(monitoringStationGetStatusCommand.queryID,
                monitoringStationGetStatusCommand.firstSatelliteID,
                monitoringStationGetStatusCommand.range,
                monitoringStationGetStatusCommand.timeout,
                getContext().getSelf()));

        return this;
    }

    private Behavior<MonitoringStationCommand> onMonitoringStationReceiveResponseCommand(
            ReceiveResponseCommand monitoringStationReceiveResponseCommand) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n-------------------------------\n");
        Formatter formatter = new Formatter(stringBuilder);
        formatter.format("Name: %s\nTime: %d\nNumber of errors: %d\nOn time responses percentage: %d %%\nErrors:\n",
                name,
                System.currentTimeMillis() - getStatusCommandsSendTimes.get(monitoringStationReceiveResponseCommand.queryID),
                monitoringStationReceiveResponseCommand.satellitesErrorMap.size(),
                monitoringStationReceiveResponseCommand.correctResponsesPercentage);

        monitoringStationReceiveResponseCommand.satellitesErrorMap.forEach((key, value) -> formatter.format("\tID: %d - %s\n", key, value));
        getContext().getLog().info(stringBuilder.toString());

        getStatusCommandsSendTimes.remove(monitoringStationReceiveResponseCommand.queryID);
        return this;
    }
}
