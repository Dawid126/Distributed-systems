package main.java.server.tcp;

import main.java.server.model.ClientRepresentation;
import main.java.server.model.ClientsCollection;
import main.java.utils.MessageType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class TCPServerClientThread extends Thread{

    Socket clientSocket;
    ClientRepresentation client;
    ClientsCollection clientsCollection;
    HashMap<String, String > lol;
    PrintWriter out;
    BufferedReader in;

    public TCPServerClientThread(Socket clientSocket, ClientsCollection clientsCollection) {
        this.clientSocket = clientSocket;
        this.clientsCollection = clientsCollection;
    }

    @Override
    public void run(){
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            while(true){
                String message = in.readLine();

                if(message.startsWith(MessageType.NAME_REQUEST.toString())) {
                    createNewClient(message.substring(2));
                } else {
                    sendMessageToAll(message);
                }
            }
        } catch (IOException e) {
            disconnectClient();
        }
    }

    private void createNewClient(String clientNewName) {
        for(ClientRepresentation client: clientsCollection.getClients()) {
            if(client.getName().equals(clientNewName)) {
                out.println(MessageType.NAME_REJECT.toString());
                return;
            }
        }
        client = new ClientRepresentation(clientNewName, clientSocket);
        out.println(MessageType.NAME_ACCEPT.toString());

        clientsCollection.addClient(client);
        System.out.println("New client connected to chat: " + client.getName());
    }

    public void sendMessageToAll(String message) {
        message = this.client.getName() + (": ") + (message);
        for(ClientRepresentation client: clientsCollection.getClients()) {
            if(!client.getName().equals(this.client.getName())){
                sendMessage(message, client);
            }
        }
    }

    public void sendMessage(String message, ClientRepresentation client) {
        try {
            PrintWriter out = new PrintWriter(client.getSocket().getOutputStream(), true);
            out.println(message);
        }
        catch (IOException e) {
            System.out.println("Error when getting output stream from client " + client.getName());
        }
    }

    public void disconnectClient(){
        if(this.client != null) {
            clientsCollection.removeClient(client);
            System.out.println("Client " + this.client.getName() + " has disconnected from chat.");
        }
    }

}

