package Dispatcher.Workers;

import Satellite.SatelliteAPI;
import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;


public class SatelliteDataRetrievalActor extends AbstractBehavior<SatelliteDataRetrievalActor.SatelliteDataRetrievalCommand>  {

    public interface SatelliteDataRetrievalCommand{}

    public static final class RequestSatelliteDataCommand implements SatelliteDataRetrievalActor.SatelliteDataRetrievalCommand {
        public final ActorRef<StatusCollectorActor.StatusCollectorCommand> replyTo;

        public RequestSatelliteDataCommand(ActorRef<StatusCollectorActor.StatusCollectorCommand> replyTo) {
            this.replyTo = replyTo;
        }
    }

    private final int satelliteID;

    public SatelliteDataRetrievalActor(ActorContext<SatelliteDataRetrievalCommand> context, int satelliteID) {
        super(context);
        this.satelliteID = satelliteID;
    }

    public static Behavior<SatelliteDataRetrievalActor.SatelliteDataRetrievalCommand> create(int satelliteID) {

        return Behaviors.setup(contex -> new SatelliteDataRetrievalActor(contex, satelliteID));
    }

    @Override
    public Receive<SatelliteDataRetrievalCommand> createReceive() {
        return newReceiveBuilder()
                .onMessage(RequestSatelliteDataCommand.class, this::onRequestSatelliteDataCommand)
                .build();
    }

    private Behavior<SatelliteDataRetrievalActor.SatelliteDataRetrievalCommand> onRequestSatelliteDataCommand (
            RequestSatelliteDataCommand requestSatelliteDataCommand) {

        requestSatelliteDataCommand.replyTo.tell(new StatusCollectorActor.ReceiveSatelliteDataCommand(SatelliteAPI.getStatus(satelliteID), satelliteID));
        return Behaviors.stopped();
    }


}
