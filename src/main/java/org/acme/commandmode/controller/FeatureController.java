package org.acme.commandmode.controller;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import org.acme.commandmode.model.Feature;
import org.acme.commandmode.model.Project;


@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/")
public class FeatureController {
    private static final Logger LOG = Logger.getLogger(FeatureController.class.getName());

    @Inject
    EntityManager em;

    @GET
    @Path("project/{id}/features")
    public Feature[] getDefault(@PathParam ("id") Integer projectId) {
        return em.createNamedQuery("Features.findByProject", Feature.class)
        .setParameter("projectId", projectId)
        .getResultList().toArray(new Feature[0]);
    }

    @POST
    @Transactional
    @Path("project/{id}/feature")
    public Response createDefault(@PathParam ("id") Integer projectId, Feature feature) {
        /*if (em.createNamedQuery("Project.findById", Project.class)
            .setParameter("projectId", projectId) == null) {
            throw new WebApplicationException("Project not found.", 404);
        } else*/ if (feature.getId() != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }
        LOG.debugv("Create {0}", feature.getDescription());
        em.persist(feature);
        return Response.ok(feature).status(201).build();
    }

    @DELETE
    @Path("feature/{id}")
    public Response deleteDefault(@PathParam ("id") Integer id) {
        Feature feature = Feature.findById(id);
        if (feature == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        feature.delete();
        return Response.status(Response.Status.OK).build();
    }
}