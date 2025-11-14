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
