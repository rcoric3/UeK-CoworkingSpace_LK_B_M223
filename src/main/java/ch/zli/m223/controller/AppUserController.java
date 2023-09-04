package ch.zli.m223.controller;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import ch.zli.m223.model.AppUser;
import ch.zli.m223.service.AppUserService;

@Path("/private")
public class AppUserController {
    @Inject
    private AppUserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AppUser> showUser() {
        return userService.findAll() ;
    }
}
