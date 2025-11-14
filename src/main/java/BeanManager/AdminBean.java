/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package BeanManager;

import jakarta.ejb.Stateless;



import EntityManager.*;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import java.util.*;
import java.util.Date;

@Stateless
public class AdminBean implements AdminBeanLocal {

    @PersistenceContext(unitName = "mypu")
    private EntityManager em;

    @Override
    public Users adminLogin(String email, String password) {
        try {
            return em.createQuery("SELECT u FROM Users u WHERE u.email = :email AND u.password = :password AND u.role='admin'", Users.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public boolean approveRecipe(int adminId, int recipeId) {
        try {
            Users admin = em.find(Users.class, adminId);
            Recipes recipe = em.find(Recipes.class, recipeId);
            recipe.setStatus("approved");

            AdminActions action = new AdminActions();
            action.setAdminId(admin);
            action.setRecipeId(recipe);
            action.setActionType("approve");
            action.setCreatedAt(new Date());

            em.persist(action);
            em.merge(recipe);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean rejectRecipe(int adminId, int recipeId, String reason) {
        try {
            Users admin = em.find(Users.class, adminId);
            Recipes recipe = em.find(Recipes.class, recipeId);
            recipe.setStatus("rejected");

            AdminActions action = new AdminActions();
            action.setAdminId(admin);
            action.setRecipeId(recipe);
            action.setActionType("reject");
            action.setReason(reason);
            action.setCreatedAt(new Date());

            em.persist(action);
            em.merge(recipe);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean removeInappropriateContent(int adminId, int recipeId, String reason) {
        try {
            Users admin = em.find(Users.class, adminId);
            Recipes recipe = em.find(Recipes.class, recipeId);
            if (recipe != null) {
                em.remove(recipe);

                AdminActions action = new AdminActions();
                action.setAdminId(admin);
                action.setActionType("remove_content");
                action.setReason(reason);
                action.setCreatedAt(new Date());
                em.persist(action);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Users> getAllUsers() {
        return em.createQuery("SELECT u FROM Users u WHERE u.role='user'", Users.class).getResultList();
    }

    @Override
    public Map<String, Long> getAdminDashboardData() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("totalUsers", em.createQuery("SELECT COUNT(u) FROM Users u WHERE u.role='user'", Long.class).getSingleResult());
        stats.put("totalRecipes", em.createQuery("SELECT COUNT(r) FROM Recipes r", Long.class).getSingleResult());
        stats.put("pendingRecipes", em.createQuery("SELECT COUNT(r) FROM Recipes r WHERE r.status='pending'", Long.class).getSingleResult());
        stats.put("totalReports", em.createQuery("SELECT COUNT(rep) FROM Reports rep", Long.class).getSingleResult());
        return stats;
    }

    @Override
    public boolean logout(int adminId) {
        Users admin = em.find(Users.class, adminId);
        return admin != null;
    }
}
