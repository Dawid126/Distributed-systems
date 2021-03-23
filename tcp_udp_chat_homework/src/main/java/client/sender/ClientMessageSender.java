package main.java.client.sender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class ClientMessageSender extends Thread {

    private String nickname;
    private final Socket tcpSocket;
    private final DatagramSocket udpSocket;
    private final DatagramSocket multicastSocket;
    private final InetAddress udpAddress;
    private final InetAddress multicastAddress;
    private final int port;
    private final int multicastPort;
    BufferedReader clientsMessageReader;

    public ClientMessageSender(String nickname, Socket tcpSocket, DatagramSocket udpSocket, DatagramSocket multicastSocket,
                               InetAddress udpAddress, int port, InetAddress multicastAddress, int multicastPort) {
        this.nickname = nickname;
        this.tcpSocket = tcpSocket;
        this.udpSocket = udpSocket;
        this.multicastSocket = multicastSocket;
        this.udpAddress = udpAddress;
        this.port = port;
        this.multicastAddress = multicastAddress;
        this.multicastPort = multicastPort;
    }

    @Override
    public void run() {

        try {
            clientsMessageReader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("To send message, first type in how you want to send it.\n" +
                    "U: UDP\nT: TCP\nM: MULTICAST\nAfter that you can type in your message.\n" +
                    "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n");

            while(true) {
                String option = clientsMessageReader.readLine();
                String message = clientsMessageReader.readLine();
                sendMessage(message, option);
            }

        } catch (IOException e) {
            System.out.println("Message sending failure.");
        }
    }

    private void sendMessage(String message, String option) throws IOException {
        if (message.isBlank()) {
            System.out.println("Cannot send empty message");
            return;
        }

        switch (option) {
            case "U": {
                sendUdpMessage(message);
                return;
            }
            case "T": {
                sendTcpMessage(message);
                return;
            }
            case "M": {
                sendMulticastMessage(message);
                return;
            }
            default:
                System.out.println("Message options: 1. U, 2. T, 3. M.");
        }
    }

    private void sendMulticastMessage(String message) throws IOException {
        message = nickname + ": " + message;
        DatagramPacket multicastPacket = new DatagramPacket(message.getBytes(), message.getBytes().length, multicastAddress, multicastPort);
        multicastSocket.send(multicastPacket);
    }

    private void sendUdpMessage(String message) throws IOException {
        DatagramPacket datagramPacket = new DatagramPacket(message.getBytes(), message.getBytes().length, udpAddress, port);
        udpSocket.send(datagramPacket);
    }

    private void sendTcpMessage(String message) throws IOException {
        PrintWriter outTCP = new PrintWriter(tcpSocket.getOutputStream(), true);
        outTCP.println(message);
    }
}
