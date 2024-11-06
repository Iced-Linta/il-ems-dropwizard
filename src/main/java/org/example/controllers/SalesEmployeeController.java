package org.example.controllers;

import io.swagger.annotations.Api;
import org.example.exceptions.DoesNotExistException;
import org.example.exceptions.FailedToCreateException;
import org.example.models.SalesEmployee;
import org.example.models.SalesEmployeeRequest;
import org.example.services.SalesEmployeeService;

import javax.ws.rs.Consumes;
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

@Api("Sales Employee API")
@Path("/api/sales-employee")
public class SalesEmployeeController {
    private final SalesEmployeeService salesEmployeeService;
    public SalesEmployeeController(SalesEmployeeService salesEmployeeService) {
        this.salesEmployeeService = salesEmployeeService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSalesEmployees() {
        try {
            return Response.ok().entity(
                    salesEmployeeService.getAllSalesEmployees()
            ).build();
        } catch (SQLException e) {
            return Response.serverError().build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSalesEmployee(
            final SalesEmployeeRequest salesEmployee
    ) {
        try {
            return Response.ok().entity(
                    salesEmployeeService.createSalesEmployee(salesEmployee)
            ).build();
        } catch (SQLException e) {
            return Response.serverError().build();
        } catch (FailedToCreateException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSalesEmployeeById(final @PathParam("id") Integer id) {
        try {
            return Response.ok().entity(
                    salesEmployeeService.getSalesEmployee(id)
            ).build();
        } catch (SQLException e) {
            return Response.serverError().build();
        } catch (DoesNotExistException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSalesEmployee(final @PathParam("id") Integer id) {
        try {
            salesEmployeeService.deleteSalesEmployee(id);
            return Response.noContent().build();
        } catch (SQLException e) {
            return Response.serverError().build();
        } catch (DoesNotExistException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSalesEmployee(
            final @PathParam("id") Integer id,
            final SalesEmployeeRequest salesEmployee
    ) {
        try {
            salesEmployeeService.updateSalesEmployee(salesEmployee, id);
            return Response.noContent().build();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return Response.serverError().build();
        } catch (DoesNotExistException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage()).build();
        }
    }
}
