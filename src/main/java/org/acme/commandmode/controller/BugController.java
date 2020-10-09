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

import org.acme.commandmode.model.InputData;
import org.acme.commandmode.model.Bug;

@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/")
public class BugController {
    private static final Logger LOG = Logger.getLogger(TestCaseController.class.getName());

    @Inject
    EntityManager em;

    @GET
    @Path("inputdata/{id}/bugs")
    public Bug[] getDefault(@PathParam("id") Integer inputDataId) {
        return em.createNamedQuery("Bug.findByInputData", Bug.class)
        .setParameter("inputDataId", inputDataId)
        .getResultList().toArray(new Bug[0]);
    }

    @POST
    @Path("inputdata/{id}/bug")
    @Transactional
    public Response postDefault(@PathParam("id") Integer inputDataId, Bug bug) {
        InputData i = InputData.findById(inputDataId);
        if(i==null) {
            throw new WebApplicationException("input data not found", 404); 
        } else if (bug.getId()!=null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }
        bug.setInputData(i);
        LOG.debugv("Create {0}", bug.getDescription());
        em.persist(bug);
        return Response.ok(bug).status(201).build();
    }
}