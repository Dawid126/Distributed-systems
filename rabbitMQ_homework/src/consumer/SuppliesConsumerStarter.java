package consumer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeoutException;

public class SuppliesConsumerStarter {

    public static void main(String[] args) throws IOException, TimeoutException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter team name");
        String input = br.readLine();
        SuppliesConsumer suppliesConsumer = new SuppliesConsumer(input);
        suppliesConsumer.start();
    }

}
