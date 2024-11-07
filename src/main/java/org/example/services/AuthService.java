package org.example.services;

import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import io.jsonwebtoken.Jwts;
import org.example.daos.AuthDao;
import org.example.exceptions.Entity;
import org.example.exceptions.FailedToCreateException;
import org.example.exceptions.InvalidException;
import org.example.models.CheckMfaRequest;
import org.example.models.IssueMfaRequest;
import org.example.models.LoginRequest;
import org.example.models.User;
import org.example.models.UserRequest;

import java.security.Key;
import java.sql.SQLException;
import java.util.Date;

public class AuthService {
    private final AuthDao authDao;
    private final Key key;

    public AuthService(final AuthDao authDao, final Key key) {
        this.authDao = authDao;
        this.key = key;
    }

    public String issueMfa(final IssueMfaRequest issueMfaRequest) {
        Verification verification =
                Verification.creator(
                        "VAd1044d715dacbc3def3d61568ef124e3",
                        issueMfaRequest.getPhone(),
                        "sms"
                ).create();
        return verification.getSid();
    }

    public void checkMfa(final CheckMfaRequest checkMfaRequest)
            throws InvalidException {
        VerificationCheck verificationCheck =
                VerificationCheck.creator("VAd1044d715dacbc3def3d61568ef124e3")
                        .setVerificationSid(checkMfaRequest.getSid())
                        .setCode(checkMfaRequest.getCode())
                        .create();
        if (!verificationCheck.getValid()) throw new InvalidException(Entity.USER, "Invalid OTP");
    }

    public String login(final LoginRequest loginRequest)
            throws SQLException, InvalidException {
        User user = authDao.getUser(loginRequest);

        System.out.println(loginRequest.getUsername());

        if (user == null) {
            throw new InvalidException(Entity.USER, "Invalid");
        }

        return generateJwtToken(user);
    }

    public int register(final UserRequest userRequest)
            throws FailedToCreateException, SQLException, InvalidException {

        int id = authDao.createUser(userRequest);

        return id;
    }

    private String generateJwtToken(final User user) {
        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 28800000))
                .claim("Role", user.getRoleId())
                .claim("User", user)
                .subject(user.getUsername())
                .issuer("ILEMS")
                .signWith(key)
                .compact();
    }
}
