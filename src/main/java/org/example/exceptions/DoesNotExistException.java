package org.example.exceptions;

public class DoesNotExistException extends Exception {
    public DoesNotExistException(final Entity entity) {
        super(entity.getName() + " does not exist");
    }
}
