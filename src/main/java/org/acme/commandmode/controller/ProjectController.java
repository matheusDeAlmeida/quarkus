package org.acme.commandmode.controller;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.persistence.EntityManager;
import javax.ws.rs.Path;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import javax.ws.rs.WebApplicationException;
import org.acme.commandmode.model.Project;
import org.jboss.logging.Logger;

import javax.inject.Inject;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/")
public class ProjectController {
    private static final Logger LOG = Logger.getLogger(ProjectController.class.getName());

    @Inject
    EntityManager em;

    @GET
    @Path("projects")
    public Project[] getDefault(){
        return em.createNamedQuery("Projects.findAll", Project.class)
        .getResultList().toArray(new Project[0]);
    }
   
    @POST
    @Transactional
    @Path("project")
    public Response createDefault(Project project) {
        if (project.getId() != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }
        LOG.debugv("Create {0}", project.getDescription());
        em.persist(project);
        return Response.ok(project).status(201).build();
    }

    @DELETE
    @Path("project/{id}")
    public Response deleteDefault(@PathParam ("id") Integer id) {
        Project project = Project.findById(id);
        if (project == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        project.delete();
        return Response.status(Response.Status.OK).build();
    }
}