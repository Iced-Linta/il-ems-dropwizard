package org.example.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientRequest {
    private String name;

    @JsonCreator
    public ClientRequest(@JsonProperty("name") final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
