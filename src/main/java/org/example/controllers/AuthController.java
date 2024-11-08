package org.example.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiKeyAuthDefinition;
import io.swagger.annotations.SecurityDefinition;
import io.swagger.annotations.SwaggerDefinition;
import org.example.exceptions.FailedToCreateException;
import org.example.exceptions.InvalidException;
import org.example.models.CheckMfaRequest;
import org.example.models.IssueMfaRequest;
import org.example.models.LoginRequest;
import org.example.models.UserRequest;
import org.example.services.AuthService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Auth API")
@Path("/api/auth")
@SwaggerDefinition(
        securityDefinition = @SecurityDefinition(
                apiKeyAuthDefinitions = {
                        @ApiKeyAuthDefinition(
                                key = HttpHeaders.AUTHORIZATION,
                                name = HttpHeaders.AUTHORIZATION,
                                in = ApiKeyAuthDefinition.ApiKeyLocation.HEADER
                        )
                }
        )
)
public class AuthController {
    AuthService authService;

    public AuthController(final AuthService authService) {
        this.authService = authService;
    }

    @POST
    @Path("/issueMfa")
    @Produces(MediaType.APPLICATION_JSON)
    public Response issueMfa(final IssueMfaRequest issueMfaRequest) {
        return Response.status(Response.Status.CREATED).entity(
                authService.issueMfa(issueMfaRequest)
        ).build();
    }

    @POST
    @Path("/checkMfa")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkMfa(final CheckMfaRequest checkMfaRequest) {
        try {
            authService.checkMfa(checkMfaRequest);
            return Response.ok().build();
        } catch (InvalidException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(final LoginRequest loginRequest) {
        try {
            return Response.ok().entity(
                    authService.login(loginRequest)
            ).build();
        } catch (SQLException e) {
            return Response.serverError().build();
        } catch (InvalidException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(final UserRequest userRequest) {
        try {
            return Response.status(Response.Status.CREATED).entity(
                    authService.register(userRequest)
            ).build();
        } catch (SQLException | FailedToCreateException e) {
            System.out.println(e.getMessage());
            return Response.serverError().build();
        } catch (InvalidException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        }
    }
}