package main.java.client.receiver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

public class UDPClientMessageReceiver extends Thread {

    private static final int bufferSize = 1024;
    private final DatagramSocket udpSocket;


    byte[] receiveBuffer = new byte[bufferSize];

    public UDPClientMessageReceiver(DatagramSocket udpSocket) {
        this.udpSocket = udpSocket;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Arrays.fill(receiveBuffer, (byte) 0);
                DatagramPacket receivedPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                udpSocket.receive(receivedPacket);

                String message = new String(receivedPacket.getData());
                System.out.println(message);
            }
        } catch (IOException e) {
            System.out.println("UDP Receiver failure");
        }
    }
}


