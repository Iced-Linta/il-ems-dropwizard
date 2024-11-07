package org.example.exceptions;

public enum Entity {
    USER("User"),
    SALES_EMPLOYEE("Sales Employee"),
    DELIVERY_EMPLOYEE("Delivery Employee"),
    CLIENT("Client");

    private final String entity;

    Entity(final String entity) {
        this.entity = entity;
    }

    public String getName() {
        return this.name();
    }

    public String getEntity() {
        return this.entity;
    }
}
