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
public interface AdminBeanLocal {

    Users adminLogin(String email, String password);
    boolean approveRecipe(int adminId, int recipeId);
    boolean rejectRecipe(int adminId, int recipeId, String reason);
    boolean removeInappropriateContent(int adminId, int recipeId, String reason);
    List<Users> getAllUsers();
    Map<String, Long> getAdminDashboardData();
    boolean logout(int adminId);
}

