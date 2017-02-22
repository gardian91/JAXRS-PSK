package com.psk.bank.resources;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CustomExceptionMapperProvider implements ExceptionMapper<NullPointerException> {

    @Override
    public Response toResponse(final NullPointerException ex) {
        return Response.serverError().entity(ex).build();
    }
}