package com.scanales.oauth;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.logging.Logger;

/**
 * Public endpoint showing a login link.
 */
@Path("/public")
public class PublicResource {

    private static final Logger LOG = Logger.getLogger(PublicResource.class);
    private static final String PREFIX = "[LOGIN] ";

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance publicPage();
    }

    @GET
    @PermitAll
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get() {
        LOG.info(PREFIX + "Serving public login page");
        return Templates.publicPage();
    }
}
