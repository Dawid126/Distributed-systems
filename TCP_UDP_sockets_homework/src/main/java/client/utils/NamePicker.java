package main.java.client.utils;

import main.java.utils.MessageType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class NamePicker {

    public static String chooseNickname(Socket tcpSocket, DatagramSocket udpSocket, int portNumber, InetAddress address) throws IOException {

        BufferedReader clientsMessageReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader inTCP = new BufferedReader(new InputStreamReader(tcpSocket.getInputStream()));
        PrintWriter outTCP = new PrintWriter(tcpSocket.getOutputStream(), true);

        while(true) {
            System.out.println("Choose your nickname:");
            String nickname = clientsMessageReader.readLine();
            outTCP.println("NR" + nickname);
            if(inTCP.readLine().equals("NA")) {
                System.out.println("Nickname " + nickname + " accepted.");
                String udpAddressUpdateMessage = MessageType.NAME_ACCEPT.toString() + nickname;
                DatagramPacket udpAddressUpdate = new DatagramPacket(udpAddressUpdateMessage.getBytes(), udpAddressUpdateMessage.getBytes().length, address, portNumber);
                udpSocket.send(udpAddressUpdate);
                return nickname;
            } else {
                System.out.println("Nickname " + nickname + " already taken. Choose new nickname.");

            }
        }
    }
}
