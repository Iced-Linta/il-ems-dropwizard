package org.example.models;

public class Client {
    private final int clientId;
    private String name;

    public Client(final int clientId, final String name) {
        this.clientId = clientId;
        this.name = name;
    }

    public int getClientId() {
        return clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
