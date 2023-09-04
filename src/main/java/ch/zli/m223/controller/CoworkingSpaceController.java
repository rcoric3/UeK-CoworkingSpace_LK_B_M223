package ch.zli.m223.controller;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.zli.m223.model.CoworkingSpace;
import ch.zli.m223.service.CoworkingSpaceService;

@Path("/coworkingSpace")
public class CoworkingSpaceController {
    @Inject
    private CoworkingSpaceService coworkingSpaceService;

    @POST
    @Path("/createCoworkingSpace")
    @Produces(MediaType.APPLICATION_JSON)
    public CoworkingSpace createNewBooking(CoworkingSpace coworkingSpace) {
        coworkingSpaceService.createCoworkingSpace(coworkingSpace);
        return coworkingSpace;
    }

    @PUT
    @Path("/addCoworkingSpaceToFavorite")
    @Produces(MediaType.APPLICATION_JSON)
    public CoworkingSpace coworkingSpace(@PathParam("id") Long id, CoworkingSpace coworkingSpace) {
        return coworkingSpaceService.addToFavorite(id, coworkingSpace);
    }
}
