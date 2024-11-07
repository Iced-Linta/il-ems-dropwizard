package org.example.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import static org.example.auth.Hasher.getHash;

public class LoginRequest {
    String username;
    String password;

    @JsonCreator
    public LoginRequest(
            @JsonProperty("username") final String username,
            @JsonProperty("password") final String password
    ) {
        this.username = username;
        this.password = getHash(password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
