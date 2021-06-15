import Dispatcher.DispatcherActor;
import MonitoringStation.MonitoringStationActor;
import akka.actor.ActorContext;
import akka.actor.typed.*;
import akka.actor.typed.javadsl.Behaviors;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AstraLinkApp {
    public static Behavior<Void> create() {
        return Behaviors.setup(
                context -> {
                    ActorRef<DispatcherActor.DispatcherCommand> dispatcher = context.spawn(DispatcherActor.create(),"dispatcher");

                    ActorRef<MonitoringStationActor.MonitoringStationCommand> station1 =
                            context.spawn(MonitoringStationActor.create("station1", dispatcher), "station1");
                    ActorRef<MonitoringStationActor.MonitoringStationCommand> station2 =
                            context.spawn(MonitoringStationActor.create("station2", dispatcher), "station2");
                    ActorRef<MonitoringStationActor.MonitoringStationCommand> station3 =
                            context.spawn(MonitoringStationActor.create("station3", dispatcher), "station3");

                    Thread.sleep(1000);

                    List<ActorRef<MonitoringStationActor.MonitoringStationCommand>> stations = Arrays.asList(station1, station2, station3);
                    Random rand = new Random();

                    for(int i = 0; i < 3; i++)
                    {
                        for(int j = 0; j < 2; j++)
                        {
                            stations.get(i).tell(new MonitoringStationActor.RequestSatellitesDataCommand(
                                    i*10 + j,
                                    100 + rand.nextInt(50),
                                    50 ,
                                    300));
                        }
                    }

                    return Behaviors.receive(Void.class)
                            .onSignal(Terminated.class, sig -> Behaviors.stopped())
                            .build();
                });
    }

    public static void main(String[] args) {
        File configFile = new File("src/main/resources/dispatcher.conf");
        Config config = ConfigFactory.parseFile(configFile);
        System.out.println("AstraLink: config: " + config);

        ActorSystem.create(AstraLinkApp.create(), "AstraLinkSystem", config);
    }
}
