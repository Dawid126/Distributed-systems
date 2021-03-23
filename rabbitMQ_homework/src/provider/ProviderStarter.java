package provider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeoutException;

public class ProviderStarter {

    public static void main(String[] args) throws IOException, TimeoutException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter provider name");
        String input = br.readLine();
        Provider provider = new Provider(input);
        provider.start();
    }
}
