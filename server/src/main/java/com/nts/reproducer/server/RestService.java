package com.nts.reproducer.server;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@ApplicationScoped
@Path("reproducer")
@Produces("application/json")
public class RestService {

    @GET
    @Path("trigger")
    public void useOpentelementry() {
        // The opentelemetry-server-filter will trigger the exception if the sampler-type is configured
        // so this endpoint does not have to do anything. It's enough that it's there to be called.
    }
}
