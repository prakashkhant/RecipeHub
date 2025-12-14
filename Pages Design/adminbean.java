adminbean

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

-------------
AdminBeanLocal

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

--------------

usebean

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package BeanManager;





import EntityManager.*;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import java.util.*;

@Stateless
public class UserBean implements UserBeanLocal {

    @PersistenceContext(unitName = "mypu")
    private EntityManager em;

    @Override
    public boolean registerUser(String fullName, String email, String password) {
        try {
            Users u = new Users();
            u.setFullName(fullName);
            u.setEmail(email);
            u.setPassword(password);
            u.setRole("user");
            u.setStatus("active");
            em.persist(u);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Users login(String email, String password) {
        try {
            return em.createQuery("SELECT u FROM Users u WHERE u.email = :email AND u.password = :password AND u.status='active'", Users.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public boolean logout(int userId) {
        Users u = em.find(Users.class, userId);
        return u != null;
    }

    @Override
    public boolean uploadRecipe(int userId, String title, String ingredients, String instructions, String category, String image) {
        try {
            Users u = em.find(Users.class, userId);
            Recipes r = new Recipes();
            r.setUserId(u);
            r.setTitle(title);
            r.setIngredients(ingredients);
            r.setInstructions(instructions);
            r.setCategory(category);
            r.setImage(image);
            r.setStatus("pending");
            em.persist(r);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Recipes> viewApprovedRecipes() {
        return em.createQuery("SELECT r FROM Recipes r WHERE r.status='approved'", Recipes.class).getResultList();
    }

    @Override
    public boolean likeRecipe(int userId, int recipeId) {
        try {
            Users user = em.find(Users.class, userId);
            Recipes recipe = em.find(Recipes.class, recipeId);
            Likes like = new Likes();
            like.setUserId(user);
            like.setRecipeId(recipe);
            em.persist(like);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean commentRecipe(int userId, int recipeId, String comment) {
        try {
            Users user = em.find(Users.class, userId);
            Recipes recipe = em.find(Recipes.class, recipeId);
            Comments c = new Comments();
            c.setUserId(user);
            c.setRecipeId(recipe);
            c.setCommentText(comment);
            em.persist(c);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean rateRecipe(int userId, int recipeId, int ratingValue) {
        try {
            Users user = em.find(Users.class, userId);
            Recipes recipe = em.find(Recipes.class, recipeId);
            Ratings rating = new Ratings();
            rating.setUserId(user);
            rating.setRecipeId(recipe);
            rating.setRatingValue(ratingValue);
            em.persist(rating);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Map<String, Long> getUserDashboardData(int userId) {
        Map<String, Long> data = new HashMap<>();
        data.put("totalRecipes", em.createQuery("SELECT COUNT(r) FROM Recipes r WHERE r.userId.userId=:uid", Long.class)
                .setParameter("uid", userId).getSingleResult());
        data.put("totalLikesGiven", em.createQuery("SELECT COUNT(l) FROM Likes l WHERE l.userId.userId=:uid", Long.class)
                .setParameter("uid", userId).getSingleResult());
        data.put("totalCommentsGiven", em.createQuery("SELECT COUNT(c) FROM Comments c WHERE c.userId.userId=:uid", Long.class)
                .setParameter("uid", userId).getSingleResult());
        return data;
    }
}

------------------
UserBeanLocal

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
