package org.example.exceptions;

public class DoesNotExistException extends Exception {
    public DoesNotExistException(final Entity entity) {
        super("Delivery Employee" + entity.getEntity() + " does not exist");
    }
}
