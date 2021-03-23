package main.java.server;

import main.java.server.receiver.MulticastServerMessageReceiver;
import main.java.server.model.ClientRepresentation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServerStarter {

    private static final String multicastAddress = "227.0.0.5";
    private static final int multicastPortNumber = 2275;

    public static void main(String[] args) {

        CopyOnWriteArrayList<ClientRepresentation> clientList = new CopyOnWriteArrayList<>();
        ServerConnector serverConnector = new ServerConnector(clientList);

        serverConnector.start();

        try {
            System.out.println("To receive multicast messages, type ACCEPT");
            BufferedReader serverInputReader = new BufferedReader(new InputStreamReader(System.in));
            String option = serverInputReader.readLine();
            if(option.equals("ACCEPT")) {
                prepareMulticastEnvironment();
            }
        } catch (IOException e) {
            System.out.println("Could not read input");
        }

    }
    private static void prepareMulticastEnvironment() throws IOException {
        MulticastSocket multicastSocket = new MulticastSocket(multicastPortNumber);
        MulticastServerMessageReceiver multicastServerMessageReceiver = new MulticastServerMessageReceiver(multicastSocket,
                InetAddress.getByName(multicastAddress));
        multicastServerMessageReceiver.start();
    }
}
