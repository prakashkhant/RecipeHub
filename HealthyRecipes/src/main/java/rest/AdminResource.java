package rest;

import ejb.AdminBeanLocal;
import Entity.Users;
import Entity.Recipes;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/admin")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdminResource {

    @Inject
    AdminBeanLocal adminBean;

    @GET
    @Path("/users")
    public List<Users> getAllUsers() {
        return adminBean.getAllUsers();
    }

    @DELETE
    @Path("/user/{id}")
    public String deleteUser(@PathParam("id") int id) {
        return adminBean.deleteUser(id);
    }

    @GET
    @Path("/recipes")
    public List<Recipes> getAllRecipes() {
        return adminBean.getAllRecipes();
    }

    @DELETE
    @Path("/recipe/{id}")
    public String deleteRecipe(@PathParam("id") int id) {
        return adminBean.deleteRecipe(id);
    }

    @POST
    @Path("/register")
    public String registerAdmin(Users admin) {
        return adminBean.registerAdmin(admin);
    }
}
