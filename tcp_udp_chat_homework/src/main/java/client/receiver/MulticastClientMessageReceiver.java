package main.java.client.receiver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastClientMessageReceiver extends Thread {

    private static final int bufferSize = 1024;
    private final MulticastSocket multicastSocket;
    private final InetAddress groupAddress;
    private final String nickname;

    public MulticastClientMessageReceiver(MulticastSocket multicastSocket, InetAddress groupAddress, String nickname) {
        this.multicastSocket = multicastSocket;
        this.groupAddress = groupAddress;
        this.nickname = nickname;
    }

    @Override
    public void run() {
        byte[] receiveBuffer = new byte[bufferSize];


        try{
            multicastSocket.joinGroup(groupAddress);
            while (true) {
                DatagramPacket receivedPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                multicastSocket.receive(receivedPacket);

                String message = new String(receivedPacket.getData());
                if(!message.startsWith(nickname)){
                    System.out.println(message);
                }

            }
        } catch (IOException e) {
            System.out.println("Multicast Receiver failure");
        }


    }
}
