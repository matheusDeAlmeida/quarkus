package org.acme.commandmode.controller;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Path;
import java.util.Arrays;
import javax.transaction.Transactional;

@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/")
public class BasicLogicController {
    
    @POST
    @Transactional
    @Path("sort")
    public BasicLogic getSort(BasicLogic basicLogic) {
        Arrays.sort(basicLogic.arrayInt);
        return basicLogic;
    }

    @POST
    @Transactional
    @Path("sort-and-double")
    public BasicLogic getSortDoubled(BasicLogic basicLogic) {
        Arrays.sort(basicLogic.arrayInt);
        for(int i = 0; i < basicLogic.arrayInt.length; i++) {
            basicLogic.arrayInt[i]=basicLogic.arrayInt[i]*2;
        }
        return basicLogic;
    }

}

class BasicLogic {
    public int[] arrayInt;

    public BasicLogic() {
    }

    public BasicLogic(int[] arrayInt) {
        this.arrayInt = arrayInt;
    }

    public int[] getArrayInt() {
        return this.arrayInt;
    }

    public void setArrayInt(int[] arrayInt) {
        this.arrayInt = arrayInt;
    }

    public BasicLogic arrayInt(int[] arrayInt) {
        this.arrayInt = arrayInt;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " arrayInt='" + getArrayInt() + "'" +
            "}";
    }
    
}