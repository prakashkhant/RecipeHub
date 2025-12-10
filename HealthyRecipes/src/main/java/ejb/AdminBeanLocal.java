/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/SessionLocal.java to edit this template
 */

/**
 *
 * @author praka
 */
package ejb;

import Entity.ActivityLog;
import Entity.Categories;
import jakarta.ejb.Local;
import java.util.List;
import Entity.Users;
import Entity.Recipes;

@Local
public interface AdminBeanLocal {

    List<Users> getAllUsers();
    String deleteUser(int userId);

    List<Recipes> getAllRecipes();
    String deleteRecipe(int recipeId);

    String registerAdmin(Users admin);
    void logActivity(Users user, String activity);
List<ActivityLog> getAllActivities();
//public List<Categories> getCategoryList();
List<Categories> getAllCategories();

}

