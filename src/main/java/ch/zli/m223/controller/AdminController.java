package ch.zli.m223.controller;

import javax.ws.rs.Produces;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import ch.zli.m223.model.AppUser;
import ch.zli.m223.service.AppUserService;

@Path("/user")
public class AdminController {

    @Inject
    private AppUserService userService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public AppUser createUser(AppUser appUser) {
        userService.createUser(appUser);
        return appUser;
    }
}
