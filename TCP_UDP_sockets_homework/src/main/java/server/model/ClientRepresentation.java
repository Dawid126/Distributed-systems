package main.java.server.model;

import java.net.Socket;

public class ClientRepresentation {

    String name;
    Socket socket;

    public ClientRepresentation(String name, Socket socket) {
        this.name = name;
        this.socket = socket;
    }

    public String getName() {
        return name;
    }

    public Socket getSocket() {
        return socket;
    }

}
