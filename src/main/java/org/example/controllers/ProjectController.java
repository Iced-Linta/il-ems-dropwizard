package org.example.controllers;

import io.swagger.annotations.Api;
import org.example.exceptions.DoesNotExistException;
import org.example.exceptions.FailedToCreateException;
import org.example.models.ProjectRequest;
import org.example.services.ProjectService;

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

@Api("Project API")
@Path("/api/project")
public class ProjectController {
    private final ProjectService projectService;
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProjects() {
        try {
            return Response.ok().entity(
                    projectService.getProjects()
            ).build();
        } catch (SQLException e) {
            return Response.serverError().build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProject(
            final ProjectRequest project
    ) {
        try {
            return Response.status(Response.Status.CREATED).entity(
                    projectService.createProject(project)
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
    public Response getProjectById(final @PathParam("id") int id) {
        try {
            return Response.ok().entity(
                    projectService.getProject(id)
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
    public Response deleteProject(final @PathParam("id") int id) {
        try {
            projectService.deleteProject(id);
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
    public Response updateProject(
            final @PathParam("id") int id,
            final ProjectRequest project
    ) {
        try {
            projectService.updateProject(project, id);
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