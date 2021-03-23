package main.java.server.receiver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastServerMessageReceiver extends Thread{

    private static final int bufferSize = 1024;
    private final MulticastSocket multicastSocket;
    private final InetAddress groupAddress;

    public MulticastServerMessageReceiver(MulticastSocket multicastSocket, InetAddress groupAddress) {
        this.multicastSocket = multicastSocket;
        this.groupAddress = groupAddress;
    }

    @Override
    public void run() {
        try{
            byte[] receiveBuffer = new byte[bufferSize];
            multicastSocket.joinGroup(groupAddress);
            while (true) {
                DatagramPacket receivedPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                multicastSocket.receive(receivedPacket);

                String message = new String(receivedPacket.getData());
                System.out.println(message);
            }
        } catch (IOException e) {
            System.out.println("Multicast Receiver failure");
        }
    }
}
