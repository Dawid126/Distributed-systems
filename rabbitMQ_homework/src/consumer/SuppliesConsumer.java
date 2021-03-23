package consumer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import utils.SupplyType;
import utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

public class SuppliesConsumer {

    private final String name;
    private final Channel channel;

    public SuppliesConsumer(String name) throws IOException, TimeoutException {
        this.name = name;
        this.channel = createTeamChannel(name, "TEAM." + name);
    }

    void start() {
        try {
            ArrayList<String> supplyTypeStringList = SupplyType.getAllSupplyTypes();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                boolean correctSupplies = true;
                System.out.println("\nEnter your orders separated by space.");
                String input = br.readLine();
                String[] ordersArray = input.split(" ");

                if ("exit".equals(input)) {
                    break;
                }

                for(String order: ordersArray){
                    if(!supplyTypeStringList.contains(order)){
                        correctSupplies = false;
                        System.out.println(order + " is not a correct supply type");
                    }
                }

                if(!correctSupplies) {
                    continue;
                }

                AMQP.BasicProperties properties = createProperties();

                for(String order: ordersArray) {
                    channel.basicPublish(Utils.exchangeName, "SUPPLYTYPE." + order, properties, order.getBytes("UTF-8"));
                    System.out.println("Request for " + order + " sent.");
                    Thread.sleep(200);
                }

            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private AMQP.BasicProperties createProperties() {
        return new AMQP.BasicProperties
                .Builder()
                .replyTo("TEAM." + name)
                .build();
    }

    private Channel createTeamChannel(String teamName, String key) throws IOException, TimeoutException {
        Channel channel = Utils.createDefaultChannel();
        channel.queueDeclare(teamName, false, false, false, null);
        channel.queueBind(teamName, Utils.exchangeName, key);
        Consumer consumer = Utils.createDefaultConsumer(channel);
        channel.basicConsume(teamName, false, consumer);
        return channel;
    }
}
