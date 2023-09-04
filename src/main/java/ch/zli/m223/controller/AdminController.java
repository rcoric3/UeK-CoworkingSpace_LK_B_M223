package ch.zli.m223.controller;

import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import ch.zli.m223.model.AppUser;
import ch.zli.m223.service.AppUserService;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdminController {


    @Inject
    private AppUserService userService;

   // @Inject
  //  JsonWebToken jwt; 

    
    @POST
    @Path("/createUser")
    @Produces(MediaType.APPLICATION_JSON)
    public AppUser createUser(AppUser appUser) {
        userService.createUser(appUser);
        return appUser;
    }
  /*
   *    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login( @RequestBody Map<String, String> requestBody){
        String username = requestBody.get("username");
        String password = requestBody.get("password");
        if(userService.isValidLogin(username, password)){
            String token = jwt.getClaim(password);
        }
    return Response.status(Response.Status.UNAUTHORIZED).build();
    }

   */
 
}
