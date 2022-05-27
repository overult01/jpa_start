package jpa_ex1.jpa_ex1.jpa_ex1.ws.jaxrs;

import javax.persistence.NoResultException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("sample")
public class SampleRest extends BaseRest {

    @Path("hello")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {
        try {
            return getResponseOk("Hello JARCH");
        } catch (BadRequestException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        } catch (NoResultException ex) {
            return Response.status(Response.Status.NO_CONTENT).entity(ex.getMessage()).build();
        } catch (Exception ex) {
            return getResponseError(ex);
        }
    }
}