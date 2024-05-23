package dev.bguina;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/moment")
public class MomentResource {

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String postMoment() {
        return "Hello from Quarkus REST";
    }
}
