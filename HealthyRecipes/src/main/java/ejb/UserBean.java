/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package ejb;

import Entity.Users;
import Entity.Recipes;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class UserBean implements UserBeanLocal {

    @PersistenceContext(unitName = "healthyPU")
    EntityManager em;

    @Override
    public Users login(String username, String password) {
        System.out.println("Query username: " + username);

        try {
            return em.createQuery(
                "SELECT u FROM Users u WHERE u.userName = :username AND u.password = :password",
                Users.class)
                .setParameter("username", username)
                .setParameter("password", password)
                .getSingleResult();
            
        } catch (Exception e) {
            return null;
        }
        
    }
@Override
public String deleteRecipe(int recipeId) {
    try {
        Recipes recipe = em.find(Recipes.class, recipeId);
        if (recipe != null) {
            em.remove(em.contains(recipe) ? recipe : em.merge(recipe));
            return "Recipe Deleted!";
        }
    } catch (Exception e) {
        return "Error deleting recipe!";
    }
    return "Recipe not found!";
}

    @Override
    public String registerUser(Users u) {
        em.persist(u);
        return "User Registered Successfully!";
    }

    @Override
    public List<Recipes> getAllRecipes() {
        return em.createNamedQuery("Recipes.findAll", Recipes.class).getResultList();
    }

    @Override
    public Recipes getRecipeById(int recipeId) {
        return em.find(Recipes.class, recipeId);
    }

    @Override
    public List<Recipes> getUserRecipes(int userId) {
        return em.createQuery(
            "SELECT r FROM Recipes r WHERE r.userId.userId = :userId", Recipes.class)
            .setParameter("userId", userId)
            .getResultList();
    }

    @Override
    public String addRecipe(Recipes recipe) {
        em.persist(recipe);
        return "Recipe Added!";
    }
@Override
public String updateRecipe(Recipes recipe) {
    try {
        em.merge(recipe);
        return "Recipe updated!";
    } catch (Exception e) {
        return "Error updating recipe!";
    }
}
@Override
public String updateUser(Users user) {
    try {
        em.merge(user);
        return "Profile Updated!";
    } catch (Exception e) {
        return "Error updating profile!";
    }
}
    @Override
    public String likeRecipe(int recipeId, int userId) {
        // Logic based on your Likes Entity
        return "Recipe Liked!";
    }
@Override
public List<String> getAllCategories() {
    return em.createQuery(
        "SELECT DISTINCT r.category FROM Recipes r WHERE r.category IS NOT NULL",
        String.class
    ).getResultList();
}

    @Override
    public String addComment(int recipeId, int userId, String commentText) {
        // Logic based on Comments Entity
        return "Comment Added!";
    }
}


