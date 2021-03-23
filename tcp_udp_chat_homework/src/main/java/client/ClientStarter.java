package main.java.client;


import main.java.client.receiver.MulticastClientMessageReceiver;
import main.java.client.receiver.TCPClientMessageReceiver;
import main.java.client.receiver.UDPClientMessageReceiver;
import main.java.client.sender.ClientMessageSender;
import main.java.client.utils.NamePicker;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ClientStarter {

    private static final String hostName = "localhost";
    private static final int portNumber = 9008;
    private static final String multicastAddress = "227.0.0.5";
    private static final int multicastPortNumber = 2275;


    public static void main(String[] args){

        Socket tcpSocket;
        DatagramSocket udpSocket;
        MulticastSocket multicastSocket;


        try {
            tcpSocket = new Socket(hostName, portNumber);
            udpSocket = new DatagramSocket(tcpSocket.getLocalPort());
            multicastSocket = new MulticastSocket(multicastPortNumber);

            ExecutorService pool = Executors.newFixedThreadPool(4);

            String nickname = NamePicker.chooseNickname(tcpSocket, udpSocket, portNumber, InetAddress.getByName(hostName));

            TCPClientMessageReceiver tcpClientMessageReceiver = new TCPClientMessageReceiver(tcpSocket);

            UDPClientMessageReceiver udpClientMessageReceiver = new UDPClientMessageReceiver(udpSocket);

            InetAddress groupAddress = InetAddress.getByName(multicastAddress);
            MulticastClientMessageReceiver multicastClientMessageReceiver = new MulticastClientMessageReceiver(multicastSocket, groupAddress, nickname);

            ClientMessageSender clientMessageSender = new ClientMessageSender(nickname,
                    tcpSocket,
                    udpSocket,
                    multicastSocket,
                    InetAddress.getByName(hostName),
                    portNumber,
                    groupAddress,
                    multicastPortNumber);

            pool.execute(tcpClientMessageReceiver);
            pool.execute(udpClientMessageReceiver);
            pool.execute(clientMessageSender);
            pool.execute(multicastClientMessageReceiver);

            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.HOURS);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
