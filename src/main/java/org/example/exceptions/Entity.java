package org.example.exceptions;

public enum Entity {
    DELIVERYEMPLOYEE("Delivery Employee");

    private final String entity;

    Entity(final String entity) {
        this.entity = entity;
    }

    public String getEntity() {
        return this.entity;
    }
}