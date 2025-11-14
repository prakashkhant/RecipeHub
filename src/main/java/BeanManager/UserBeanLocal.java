/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/SessionLocal.java to edit this template
 */
package BeanManager;
import EntityManager.*;
import jakarta.ejb.Local;
import java.util.List;
import java.util.Map;

@Local
public interface UserBeanLocal {

    boolean registerUser(String fullName, String email, String password);
    Users login(String email, String password);
    boolean logout(int userId);

    boolean uploadRecipe(int userId, String title, String ingredients, String instructions, String category, String image);
    List<Recipes> viewApprovedRecipes();
    boolean likeRecipe(int userId, int recipeId);
    boolean commentRecipe(int userId, int recipeId, String comment);
    boolean rateRecipe(int userId, int recipeId, int ratingValue);
    Map<String, Long> getUserDashboardData(int userId);
}
