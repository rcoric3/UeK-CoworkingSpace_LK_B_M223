package ch.zli.m223.controller;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.model.CoworkingSpace;
import ch.zli.m223.service.CoworkingSpaceService;

@Path("/coworkingSpace")
public class CoworkingSpaceController {
    @Inject
    private CoworkingSpaceService coworkingSpaceService;

    @POST
    @RolesAllowed({ "admin" })
    @Path("/createCoworkingSpace")
    @Tag(name = "createCoworkingSpace", description = "Creats new coworkingspace")
    @Produces(MediaType.APPLICATION_JSON)

    public CoworkingSpace createNewBooking(CoworkingSpace coworkingSpace) {
        coworkingSpaceService.createCoworkingSpace(coworkingSpace);
        return coworkingSpace;
    }

    @PUT
    @RolesAllowed({ "admin" })
    @Path("/addCoworkingSpaceToFavorite")
    @Tag(name = "addCoworkingSpaceToFavorite", description = "Adds coworkingspace to favorite")
    @Consumes(MediaType.APPLICATION_JSON)
    public CoworkingSpace coworkingSpace(@PathParam("id") Long id, CoworkingSpace coworkingSpace) {
        return coworkingSpaceService.addToFavorite(id, coworkingSpace);
    }
}
