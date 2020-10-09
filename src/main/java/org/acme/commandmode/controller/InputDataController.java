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
import org.acme.commandmode.model.Step;

@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/")
public class InputDataController {
    private static final Logger LOG = Logger.getLogger(TestCaseController.class.getName());

    @Inject
    EntityManager em;

    @GET
    @Path("/step/{id}/inputdata")
    public InputData[] getDefault(@PathParam("id") Integer stepId) {
        return em.createNamedQuery("InputData.findByStep", InputData.class)
        .setParameter("stepId", stepId)
        .getResultList().toArray(new InputData[0]);
    }

    @POST
    @Transactional
    @Path("step/{id}/inputdata")
    public Response postDefault(@PathParam("id") Integer stepId, InputData inputData) {
        Step s = Step.findById(stepId);
        if (s == null) {
            throw new WebApplicationException("Step not found.", 404);
        } else if (inputData.getId()!=null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }
        inputData.setStep(s);
        LOG.debugv("Create {0}", inputData.getDescription());
        em.persist(inputData);
        return Response.ok(inputData).status(201).build();
    }

    @DELETE
    @Path("inputdata/{id}")
    public Response deleteDefault(@PathParam ("id") Integer id) {
        InputData s = InputData.findById(id);
        if (s == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        s.delete();
        return Response.status(Response.Status.OK).build();
    }
}