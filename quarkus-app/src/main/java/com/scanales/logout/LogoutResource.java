package com.scanales.logout;

import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;

import org.jboss.logging.Logger;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

/**
 * Simple endpoint to clear the q_session cookie and redirect the user
 * to the home page.
 */
@Path("/logout")
public class LogoutResource {

    private static final Logger LOG = Logger.getLogger(LogoutResource.class);
    private static final String PREFIX = "[LOGIN] ";

    @GET
    public Response logout() {
        LOG.info(PREFIX + "Processing logout request");
        return Response.status(Response.Status.SEE_OTHER)
                .header(HttpHeaders.LOCATION, "/")
                .header(HttpHeaders.SET_COOKIE, "q_session=; Path=/; Max-Age=0; HttpOnly; Secure")
                .build();
    }
}
