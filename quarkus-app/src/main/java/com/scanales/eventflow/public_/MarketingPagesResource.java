package com.scanales.eventflow.public_;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
@PermitAll
@Produces(MediaType.TEXT_HTML)
public class MarketingPagesResource {

  @CheckedTemplate(basePath = "pages")
  static class Templates {
    public static native TemplateInstance eventos();

    public static native TemplateInstance comunidad();

    public static native TemplateInstance docs();

    public static native TemplateInstance contacto();
  }

  @GET
  @Path("eventos")
  public TemplateInstance eventos() {
    return Templates.eventos();
  }

  @GET
  @Path("comunidad")
  public TemplateInstance comunidad() {
    return Templates.comunidad();
  }

  @GET
  @Path("docs")
  public TemplateInstance docs() {
    return Templates.docs();
  }

  @GET
  @Path("contacto")
  public TemplateInstance contacto() {
    return Templates.contacto();
  }
}
