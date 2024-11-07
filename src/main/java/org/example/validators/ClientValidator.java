package org.example.validators;

import org.example.exceptions.Entity;
import org.example.exceptions.InvalidException;
import org.example.models.ClientRequest;

public class ClientValidator {
    public void validateClient(
            ClientRequest clientRequest) throws
            InvalidException {

        if (clientRequest.getName().length() > 50) {
            throw new InvalidException(Entity.CLIENT,
                    "Name greater than 50 characters.");
        }


        if (clientRequest.getName().isEmpty() ||
                clientRequest.getName() == null) {
            throw new InvalidException(Entity.CLIENT,
                    "Name is blank. Please enter a name.");
        }
    }
}
