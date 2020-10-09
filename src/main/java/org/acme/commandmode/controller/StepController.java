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

import org.acme.commandmode.model.Step;
import org.acme.commandmode.model.TestCase;

@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/")
public class StepController {
    private static final Logger LOG = Logger.getLogger(TestCaseController.class.getName());

    @Inject
    EntityManager em;

    @GET
    @Path("testcase/{id}/steps")
    public Step[] getDefault(@PathParam("id") Integer testCaseId ) {
        return em.createNamedQuery("Steps.findByTestCase", Step.class)
        .setParameter("testCaseId", testCaseId)
        .getResultList().toArray(new Step[0]);
    }

    @POST
    @Transactional
    @Path("testcase/{id}/step")
    public Response postDefault(@PathParam("id") Integer testcaseId, Step step) {
        TestCase testCase = TestCase.findById(testcaseId);
        if (testCase == null) {
            throw new WebApplicationException("Test case not found.", 404);
        } else if(step.getId() != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }
        step.setTestCase(testCase);
        LOG.debugv("Create {0}", testCase.getDescription());
        em.persist(step);
        return Response.ok(step).status(201).build();
    }

    @DELETE
    @Path("step/{id}")
    public Response deleteDefault(@PathParam ("id") Integer id) {
        Step s = Step.findById(id);
        if (s == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        s.delete();
        return Response.status(Response.Status.OK).build();
    }
}