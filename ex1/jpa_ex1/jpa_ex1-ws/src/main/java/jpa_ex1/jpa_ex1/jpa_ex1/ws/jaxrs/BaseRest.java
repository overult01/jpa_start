package jpa_ex1.jpa_ex1.jpa_ex1.ws.jaxrs;

import javax.ws.rs.core.Response;

public class BaseRest {
    public Response getResponseOk() {
        return getResponseOk(null);
    }

    public Response getResponseOk(Object entity) {
        return Response
                .status(Response.Status.OK)
                .entity(entity)
                .build();
    }

    public Response getResponseError(Exception ex) {
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(ex.getMessage())
                .build();
    }
}