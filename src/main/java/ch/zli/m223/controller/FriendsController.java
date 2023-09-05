package ch.zli.m223.controller;


import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.model.Friends;
import ch.zli.m223.service.FriendsService;

@Path("/friends")
public class FriendsController {

    @Inject
    private FriendsService friendsService;

    @POST
    @RolesAllowed({"admin", "user"})
    @Path("/addFriend/userId/{userId}/friendId/{friendId}")
    @Tag(name = "addFriend", description = "You can add a new friend")
    @Produces(MediaType.APPLICATION_JSON)
    public Friends addFriend(
        @PathParam("userId") Long userId, 
        @PathParam("friendId") Long friendId){
     
        return friendsService.createFriend(userId, friendId);
    }

}
