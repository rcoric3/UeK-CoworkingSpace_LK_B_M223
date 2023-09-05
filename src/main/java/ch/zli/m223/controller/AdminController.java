package ch.zli.m223.controller;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.model.AppUser;
import ch.zli.m223.service.AppUserService;
@Path("/admin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdminController {

    @Inject
    private AppUserService userService;

    @POST
    @Path("/login")
    @PermitAll
    @Tag(name = "login", description = "Login endpoint")
    public String login(AppUser user) {
        return userService.login(user.getEmail(), user.getPassword());
    }
}
