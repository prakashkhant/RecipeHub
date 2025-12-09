package rest;

import ejb.UserBeanLocal;
import Entity.Users;
import Entity.Recipes;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserBeanLocal userBean;

    @POST
    @Path("/login")
    public Users login(Users u) {
        return userBean.login(u.getUserName(), u.getPassword());
    }

    @POST
    @Path("/register")
    public String registerUser(Users u) {
        return userBean.registerUser(u);
    }

    @GET
    @Path("/recipes")
    public List<Recipes> getAllRecipes() {
        return userBean.getAllRecipes();
    }

    @GET
    @Path("/recipe/{id}")
    public Recipes getRecipe(@PathParam("id") int id) {
        return userBean.getRecipeById(id);
    }

    @GET
    @Path("/{userId}/recipes")
    public List<Recipes> getUserRecipes(@PathParam("userId") int userId) {
        return userBean.getUserRecipes(userId);
    }

    @POST
    @Path("/addRecipe")
    public String addRecipe(Recipes recipe) {
        return userBean.addRecipe(recipe);
    }

    @POST
    @Path("/like/{recipeId}/{userId}")
    public String likeRecipe(@PathParam("recipeId") int recipeId,
                             @PathParam("userId") int userId) {
        return userBean.likeRecipe(recipeId, userId);
    }

   
}
