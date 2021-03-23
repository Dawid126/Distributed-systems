package provider;

import com.rabbitmq.client.*;
import utils.SupplyType;
import utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

public class Provider {

    private final String name;
    private final Channel channel;
    private final Channel receiveChannel;

    public Provider(String name) throws IOException, TimeoutException {
        this.name = name;
        this.channel = Utils.createDefaultChannel();
        this.receiveChannel = Utils.createDefaultChannel();
    }

    void start() {
        String[] supplyTypesArray = null;
        try {
            ArrayList<String> supplyTypeStringList = SupplyType.getAllSupplyTypes();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Consumer consumer = createConsumerForProvider();
            receiveChannel.basicQos(1);

            boolean correctSupplies = false;
            while (!correctSupplies) {
                ArrayList<String> supplyTypesArrayCopy = new ArrayList<>();
                System.out.println("Enter supply types handled by this provider.");
                String input = br.readLine();
                supplyTypesArray = input.split(" ");

                if ("exit".equals(input)) {
                    break;
                }

                boolean flag = true;
                for (String supplyType : supplyTypesArray) {
                    if(supplyTypesArrayCopy.contains(supplyType)) {
                        System.out.println(supplyType + " is already defined. Cannot define a supply type more than once.");
                        flag = false;
                        break;
                    }
                    if (!supplyTypeStringList.contains(supplyType)) {
                        System.out.println(supplyType + " is not a correct supply type.");
                        flag = false;
                        break;
                    }

                    supplyTypesArrayCopy.add(supplyType);
                }
                if(flag) {
                    correctSupplies = true;
                }
            }

            for (String supplyType : supplyTypesArray) {
                createNewSupplyQueue(supplyType, consumer);
            }
        } catch(IOException e) {

        }
    }

    private Consumer createConsumerForProvider() {
        return new DefaultConsumer(receiveChannel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("New order received: " + message);
                System.out.println("Order for " + message + " received from " + properties.getReplyTo().substring(5) + " has been completed successfully");

                String response = "Your order for " + message + " has been completed successfully by " + name;

                channel.basicPublish(
                        Utils.exchangeName,
                        properties.getReplyTo(),
                        null,
                        response.getBytes("UTF-8"));

                System.out.println("Response sent: " + response);

                receiveChannel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
    }

    private void createNewSupplyQueue(String supplyType, Consumer consumer) throws IOException {
        receiveChannel.queueDeclare(supplyType, false, false, false, null);
        channel.exchangeDeclare(Utils.exchangeName, BuiltinExchangeType.TOPIC);
        receiveChannel.queueBind(supplyType, Utils.exchangeName, "SUPPLYTYPE." + supplyType);
        receiveChannel.basicConsume(supplyType, false, consumer);
    }
}
