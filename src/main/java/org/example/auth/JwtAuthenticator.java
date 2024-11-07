package org.example.auth;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.example.models.JwtToken;
import org.example.models.User;
import org.example.models.UserRole;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Optional;

public class JwtAuthenticator implements Authenticator<String, JwtToken> {
    SecretKey key;

    public JwtAuthenticator(final SecretKey key) {
        this.key = key;
    }

    @Override
    public Optional<JwtToken> authenticate(final String token) throws
            AuthenticationException {
        try {
            Integer roleId = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .get("Role", Integer.class);

            User user = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .get("User", User.class);

            JwtToken jwtToken = new JwtToken(new UserRole(roleId), user);

            return Optional.of(jwtToken);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
