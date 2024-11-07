package org.example.exceptions;

public enum Entity {
    DELIVERY_EMPLOYEE("Delivery Employee"),
    PROJECT("Project")
    USER("User"),
    SALES_EMPLOYEE("Sales Employee"),
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
