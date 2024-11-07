package org.example.auth;

import io.dropwizard.auth.Authorizer;
import org.example.models.JwtToken;

import javax.annotation.Nullable;
import javax.ws.rs.container.ContainerRequestContext;

public class RoleAuthorisor implements Authorizer<JwtToken> {
    @Override
    public boolean authorize(final JwtToken jwtToken, final String role) {
        return jwtToken.getUserRole().getRoleName().equals(role);
    }

    @Override
    public boolean authorize(final JwtToken principal, final String role,
                             final @Nullable ContainerRequestContext requestContext) {
        return principal.getUserRole().getRoleName().equals(role);
    }
}
