package main.java.server;

import main.java.server.model.*;
import main.java.server.tcp.TCPServerClientThread;
import main.java.server.udp.UDPServerClientThread;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerConnector extends Thread{

    private static final int portNumber = 9008;

    ExecutorService pool = Executors.newCachedThreadPool();
    ServerSocket serverSocket;
    DatagramSocket udpSocket;
    CopyOnWriteArrayList<ClientRepresentation> clientList;

    public ServerConnector(CopyOnWriteArrayList<ClientRepresentation> clientList) {
        this.clientList = clientList;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(portNumber);
            udpSocket = new DatagramSocket(portNumber);
            ClientsCollection clients = new ClientsCollection();

            while(true){

                Socket clientSocket = serverSocket.accept();

                //creating server thread which will receive TCP messages from given client and sent them to all other clients using TCP
                TCPServerClientThread tcpServerClientThread = new TCPServerClientThread(clientSocket, clients);
                //creating server thread which will receive UDP messages from given client and sent them to all other clients using UDP
                UDPServerClientThread udpServerClientThread = new UDPServerClientThread(udpSocket, clients);
                pool.execute(tcpServerClientThread);
                pool.execute(udpServerClientThread);

            }
        } catch (IOException e) {
            System.out.println("Error when accepting new connection");
        }
    }
}
