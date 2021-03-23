package main.java.server.udp;

import main.java.server.model.*;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.util.Arrays;

public class UDPServerClientThread extends Thread {

    private static final int bufferSize = 1024;

    private final DatagramSocket udpSocket;
    private final ClientsCollection clientsCollection;
    private SocketAddress senderSocketAddress;

    public UDPServerClientThread(DatagramSocket udpSocket, ClientsCollection clientsCollection) {
        this.udpSocket = udpSocket;
        this.clientsCollection = clientsCollection;
    }

    @Override
    public void run() {

        byte[] receiveBuffer = new byte[bufferSize];

        while(true){
            try {
                Arrays.fill(receiveBuffer, (byte) 0);
                DatagramPacket receivedPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                udpSocket.receive(receivedPacket);

                String message = new String(receivedPacket.getData());
                senderSocketAddress = receivedPacket.getSocketAddress();

                if(message.startsWith("NA")) {
                    String nickname = message.substring(2).replace("\u0000", "").trim();
                    clientsCollection.addUdpAddress(nickname, senderSocketAddress);
                } else {
                    sendMessageToAll(message);
                }
            } catch (IOException e) {
                System.out.println("Error when sending message");
            }
        }
    }

    public void sendMessageToAll(String message) throws IOException {
        String name = clientsCollection.getClientNameFromUdpAddress(senderSocketAddress);
        message = (name + ": " + message);

        for(SocketAddress clientSocketAddress: clientsCollection.getUdpAddresses()) {
            if(!senderSocketAddress.equals(clientSocketAddress)){
                sendMessage(message, clientSocketAddress);
            }
        }
    }

    public void sendMessage(String message, SocketAddress clientSocketAddress) throws IOException {
        DatagramPacket datagramPacket = new DatagramPacket(message.getBytes(), message.getBytes().length, clientSocketAddress);
        udpSocket.send(datagramPacket);
    }
}
