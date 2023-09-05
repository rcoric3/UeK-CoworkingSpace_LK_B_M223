package ch.zli.m223.controller;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.model.AppUser;
import ch.zli.m223.service.AppUserService;

@Path("/users")
public class AppUserController {
    @Inject
    private AppUserService userService;

    @GET
    @RolesAllowed({ "admin" })
    @Path("/getAll")
    @Tag(name = "getAll", description = "Should claim all User")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<AppUser> showUser() {
        return userService.findAll();
    }

    @POST
    @Path("/createUser")
    @Tag(name = "createUser", description = "Creats new user")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @PermitAll
    public AppUser createUser(AppUser appUser) {
        userService.createUser(appUser);
        return appUser;
    }

    @PUT
    @RolesAllowed({ "admin" })
    @Path("/manageMembers/{userId}")
    @Tag(name = "manageMembers", description = "Manages Members")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public AppUser manageMembers(@PathParam("userId") Long id, AppUser appUser) {
        return userService.updateUser(id, appUser);
    }

    @DELETE
    @RolesAllowed({ "admin" })
    @Tag(name = "deleteUser", description = " Deletes User in Db")
    @Path("/deleteUser/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteBooking(@PathParam("userId") Long id) {
        userService.deleteUser(id);
    }

}
