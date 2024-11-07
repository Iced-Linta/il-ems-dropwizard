package org.example.services;

import org.example.daos.ClientDao;
import org.example.exceptions.DoesNotExistException;
import org.example.exceptions.Entity;
import org.example.exceptions.FailedToCreateException;
import org.example.exceptions.InvalidException;
import org.example.mappers.ClientMapper;
import org.example.models.Client;
import org.example.models.ClientRequest;
import org.example.responses.ClientResponse;
import org.example.validators.ClientValidator;

import java.sql.SQLException;
import java.util.List;

public class ClientService {
    ClientDao clientDao;
    ClientValidator clientValidator;

    public ClientService(final ClientDao clientDao,
                         final ClientValidator clientValidator) {
        this.clientDao = clientDao;
        this.clientValidator = clientValidator;
    }

    public List<ClientResponse> getAllClients() throws SQLException {
        return ClientMapper.mapClientListToClientResponseList(
                clientDao.getAllClients());
    }

    public ClientResponse getClientById(final int id)
            throws SQLException, DoesNotExistException {
        Client client = clientDao.getClientById(id);

        if (client == null) {
            throw new DoesNotExistException(Entity.CLIENT);
        }

        return ClientMapper.mapClientToClientResponse(client);
    }

    public int createClient(final ClientRequest clientRequest)
            throws FailedToCreateException, SQLException, InvalidException {
        clientValidator.validateClient(clientRequest);

        final int id = clientDao.createClient(clientRequest);

        if (id == -1) {
            throw new FailedToCreateException(Entity.CLIENT);
        }

        return id;
    }

    public void updateClient(final int id, final ClientRequest clientRequest)
            throws InvalidException, SQLException, DoesNotExistException {
        clientValidator.validateClient(clientRequest);
        Client clientToUpdate = clientDao.getClientById(id);

        if (clientToUpdate == null) {
            throw new DoesNotExistException(Entity.CLIENT);
        }

        clientDao.updateClient(id, clientRequest);
    }

    public void deleteClientById(final int id)
            throws SQLException, DoesNotExistException {
        Client clientToDelete = clientDao.getClientById(id);

        if (clientToDelete == null) {
            throw new DoesNotExistException(Entity.CLIENT);
        }

        clientDao.deleteClient(id);
    }
}
