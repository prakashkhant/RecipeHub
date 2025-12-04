/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/SessionLocal.java to edit this template
 */
package ejb;

import jakarta.ejb.Local;
import java.util.List;
import Entity.Users;
import Entity.Recipes;

@Local
public interface UserBeanLocal {

    Users login(String username, String password);
    String registerUser(Users u);
String updateUser(Users user);
    List<Recipes> getAllRecipes();
    Recipes getRecipeById(int recipeId);
public List<String> getAllCategories();

    List<Recipes> getUserRecipes(int userId);
    String addRecipe(Recipes recipe);
String deleteRecipe(int recipeId);

String updateRecipe(Recipes recipe);

    String likeRecipe(int recipeId, int userId);
    String addComment(int recipeId, int userId, String commentText);
}

