package main.java.client.receiver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPClientMessageReceiver extends Thread {

    private final Socket socket;

    public TCPClientMessageReceiver(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try{
            BufferedReader inTCP = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while(true) {
                String message = inTCP.readLine();
                System.out.println(message);
            }
        } catch (IOException e) {
            System.out.println("Connection to server lost");
        }

    }
}
