package org.example.mappers;

import org.example.models.Client;
import org.example.responses.ClientResponse;

import java.util.List;
import java.util.stream.Collectors;

public class ClientMapper {
    public static ClientResponse mapClientToClientResponse(
            final Client client) {
        return new ClientResponse(client.getClientID(), client.getName());
    }

    public static List<ClientResponse> mapClientListToClientResponseList(
            final List<Client> clients) {
        return clients.stream()
                .map(ClientMapper::mapClientToClientResponse)
                .collect(Collectors.toList());
    }
}
