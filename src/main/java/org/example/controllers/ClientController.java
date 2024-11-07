package org.example.controllers;

import io.swagger.annotations.Api;
import org.example.exceptions.DoesNotExistException;
import org.example.exceptions.FailedToCreateException;
import org.example.exceptions.InvalidException;
import org.example.models.ClientRequest;
import org.example.services.ClientService;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Client API")
@Path("/api/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(
            final ClientService clientService
    ) {
        this.clientService = clientService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClients() {
        try {
            return Response.ok().entity(
                    clientService.getAllClients()
            ).build();
        } catch (SQLException e) {
            return Response.status(
                    Response.Status.INTERNAL_SERVER_ERROR
            ).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClientById(final @PathParam("id") int id) {
        try {
            return Response.ok().entity(
                    clientService.getClientById(id)
            ).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        } catch (DoesNotExistException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage()).build();
        }

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createClient(
            final ClientRequest clientRequest) {
        try {
            return Response.ok()
                    .entity(clientService.createClient(
                            clientRequest))
                    .build();
        } catch (SQLException | FailedToCreateException e) {
            return Response.serverError().entity(e.getMessage()).build();
        } catch (InvalidException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateClient(
            @PathParam("id") final int id,
            final ClientRequest clientRequest
    ) {
        try {
            clientService.updateClient(id,
                    clientRequest);
            return Response.noContent().build();
        } catch (InvalidException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        } catch (DoesNotExistException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        } catch (SQLException e) {
            return Response.serverError()
                    .entity(e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteClient(@PathParam("id") final int id) {
        try {
            clientService.deleteClientById(id);
            return Response.noContent().build();
        } catch (SQLException e) {
            return Response.serverError()
                    .entity(e.getMessage())
                    .build();
        } catch (DoesNotExistException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        }
    }
}