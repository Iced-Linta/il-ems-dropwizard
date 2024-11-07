package org.example.models;

public class Client {
    private int clientID;
    private String name;

    public Client(final int clientID, final String name) {
        this.clientID = clientID;
        this.name = name;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(final int clientID) {
        this.clientID = clientID;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
