/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/SessionLocal.java to edit this template
 */
package ejb;

import Entity.Categories;
import Entity.Comments;
import Entity.Likes;
import jakarta.ejb.Local;
import java.util.List;
import Entity.Users;
import Entity.Recipes;

@Local
public interface UserBeanLocal {

    Users login(String username, String password);
    String registerUser(Users u);
String updateUser(Users user);

List<Users> getAllUsers();
List<Recipes> getAllRecipes();
void deleteUser(int userId);
public Users getUserById(int userId);

public List<Recipes> getAllRecipesDESC();
 
    Recipes getRecipeById(int recipeId);
public List<Categories> getAllCategories();

    List<Recipes> getUserRecipes(int userId);
    String addRecipe(Recipes recipe);
String deleteRecipe(int recipeId);

String updateRecipe(Recipes recipe);
void toggleLike(int recipeId, int userId);
boolean isRecipeLiked(int recipeId, int userId);
long getLikeCount(int recipeId);
//public List<Categories> getCategoryList();
List<Likes> getUserLikes(int userId);
public Categories getCategoryById(int id);
Recipes getRecipeByLike(int recipeId);

void removeLike(int recipeId, int userId);

    String likeRecipe(int recipeId, int userId);
   // String addComment(int recipeId, int userId, String commentText);
    
    List<Comments> getCommentsByRecipe(int recipeId);
void addComment(int recipeId, int userId, String text);
List<Comments> getUserComments(int userId);
void deleteComment(int commentId);
//void updateComment(int commentId, String newText);
public boolean isUsernameExists(String username);
public boolean isEmailExists(String email);


//for admin
List<Recipes> getRecipesByUser(int userId);
List<Recipes> getLikedRecipes(int userId);
void addCategory(Categories c);
void updateCategory(Categories c);
void deleteCategory(int id);
public List<Comments> getAllComments();
}

