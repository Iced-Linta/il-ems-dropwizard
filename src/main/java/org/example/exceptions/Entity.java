package org.example.exceptions;

public enum Entity {
    DELIVERY_EMPLOYEE("Delivery Employee"),
    PROJECT("Project"),
    SALES_EMPLOYEE("Sales Employee"),
    CLIENT("Client"),
    USER("User");

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
