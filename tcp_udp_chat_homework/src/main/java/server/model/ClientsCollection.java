package main.java.server.model;

import java.net.SocketAddress;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ClientsCollection {

    private CopyOnWriteArrayList<ClientRepresentation> clientList = new CopyOnWriteArrayList<>();
    private LinkedHashMap<SocketAddress, String> clientsUdpAdresses = new LinkedHashMap<>();


    public void removeClient(ClientRepresentation clientToRemove) {
        clientList.remove(clientToRemove);
        synchronized (this) {
            for(SocketAddress clientSocketAddress: clientsUdpAdresses.keySet()) {
                if(clientsUdpAdresses.get(clientSocketAddress).equals(clientToRemove.getName())) {
                    clientsUdpAdresses.remove(clientSocketAddress);
                }
            }
        }
    }

    public void addClient(ClientRepresentation client) {
        clientList.add(client);
    }

    public void addUdpAddress(String clientName, SocketAddress udpAddress) {
        synchronized (this) {
            clientsUdpAdresses.put(udpAddress, clientName);
        }
    }

    public Set<SocketAddress> getUdpAddresses() {
        synchronized (this) {
            return new LinkedHashSet<>(clientsUdpAdresses.keySet());
        }
    }

    public CopyOnWriteArrayList<ClientRepresentation> getClients() {
        return clientList;
    }

    public String getClientNameFromUdpAddress(SocketAddress clientUdpAddress) {
        synchronized (this) {
            return clientsUdpAdresses.get(clientUdpAddress);
        }
    }
}
