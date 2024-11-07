package org.example.responses;

public class ClientResponse {
    private final int clientId;
    private final String name;

    public ClientResponse(final int clientId, final String name) {
        this.clientId = clientId;
        this.name = name;
    }

    public int getClientId() {
        return clientId;
    }

    public String getName() {
        return name;
    }

}
