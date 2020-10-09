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
import org.acme.commandmode.model.TestCase;

@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/")
public class TestCaseController {
    private static final Logger LOG = Logger.getLogger(TestCaseController.class.getName());

    @Inject
    EntityManager em;

    @GET
    @Path("feature/{id}/testcases")
    public TestCase[] getDefault(@PathParam ("id") Integer featureId) {
        return em.createNamedQuery("TestCases.findByFeature", TestCase.class)
        .setParameter("featureId", featureId)
        .getResultList().toArray(new TestCase[0]);
    }

    @POST
    @Transactional
    @Path("feature/{id}/testcase")
    public Response createDefault(@PathParam ("id") Integer featureId, TestCase testCase) {
        Feature feature = Feature.findById(featureId);
        if (feature == null) {
            throw new WebApplicationException("Feature not found.", 404);
        } else if(testCase.getId() != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }
        testCase.setFeature(feature);
        LOG.debugv("Create {0}", testCase.getDescription());
        em.persist(testCase);
        return Response.ok(testCase).status(201).build();
    }

    @DELETE
    @Path("testcase/{id}")
    public Response deleteDefault(@PathParam ("id") Integer id) {
        TestCase testCase = TestCase.findById(id);
        if (testCase == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        testCase.delete();
        return Response.status(Response.Status.OK).build();
    }
    
}