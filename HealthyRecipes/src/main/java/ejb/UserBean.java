/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package ejb;

import Entity.Comments;
import Entity.Likes;
import Entity.LikesPK;
import Entity.Users;
import Entity.Recipes;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Date;
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
    public List<Users> getAllUsers() {
        return em.createNamedQuery("Users.findAll", Users.class).getResultList();
    }

    @Override
    public List<Recipes> getAllRecipes() {
        return em.createNamedQuery("Recipes.findAll", Recipes.class).getResultList();
    }
     @Override
    public List<Recipes> getAllRecipesDESC() {
        return em.createNamedQuery("Recipes.findAllDesc", Recipes.class).getResultList();
    }

    @Override
    public void deleteUser(int userId) {
        Users u = em.find(Users.class, userId);
        if (u != null) {
            em.remove(u);
        }
    }

    @Override
    public Users getUserById(int userId) {
        return em.find(Users.class, userId);
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
    public boolean isRecipeLiked(int recipeId, int userId) {
        try {
            Long count = em.createQuery(
                    "SELECT COUNT(l) FROM Likes l WHERE l.likesPK.recipeId = :recipeId AND l.likesPK.userId = :userId",
                    Long.class
            )
                    .setParameter("recipeId", recipeId)
                    .setParameter("userId", userId)
                    .getSingleResult();
            return count > 0;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void toggleLike(int recipeId, int userId) {
        LikesPK pk = new LikesPK(recipeId, userId);
        Likes like = em.find(Likes.class, pk);

        if (like != null) {
            em.remove(like); // UNLIKE
        } else {
            like = new Likes(pk, new Date());
            em.persist(like); // LIKE
        }
    }

    @Override
    public long getLikeCount(int recipeId) {
        return em.createQuery(
                "SELECT COUNT(l) FROM Likes l WHERE l.likesPK.recipeId = :recipeId",
                Long.class
        )
                .setParameter("recipeId", recipeId)
                .getSingleResult();
    }

// for usedashboard all liked recipes
    @Override
    public List<Likes> getUserLikes(int userId) {
        return em.createQuery("SELECT l FROM Likes l WHERE l.likesPK.userId = :uid", Likes.class)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public Recipes getRecipeByLike(int recipeId) {
        return em.find(Recipes.class, recipeId);
    }

    @Override
    public void removeLike(int recipeId, int userId) {
        try {
            LikesPK pk = new LikesPK(recipeId, userId); // FIXED ORDER!
            Likes like = em.find(Likes.class, pk);

            if (like != null) {
                em.remove(like);
                em.flush(); // Ensure DB update applies immediately
            }
        } catch (Exception e) {
            System.out.println("Error removing like: " + e.getMessage());
        }
    }

//  comments section
    @Override
    public List<Comments> getCommentsByRecipe(int recipeId) {
        return em.createQuery(
                "SELECT c FROM Comments c WHERE c.recipeId.recipeId = :rid ORDER BY c.createdAt DESC",
                Comments.class
        )
                .setParameter("rid", recipeId)
                .getResultList();
    }

    @Override
    public void addComment(int recipeId, int userId, String text) {
        Recipes recipe = em.find(Recipes.class, recipeId);
        Users user = em.find(Users.class, userId);

        if (recipe != null && user != null) {
            Comments comment = new Comments();
            comment.setText(text);
            comment.setCreatedAt(new Date());
            comment.setRecipeId(recipe);
            comment.setUserId(user);

            em.persist(comment);
        }
    }

    @Override
    public List<Comments> getUserComments(int userId) {
        return em.createQuery(
                "SELECT c FROM Comments c WHERE c.userId.userId = :uid ORDER BY c.createdAt DESC",
                Comments.class
        )
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public void deleteComment(int commentId) {
        Comments c = em.find(Comments.class, commentId);
        if (c != null) {
            em.remove(c);
        }
    }

    @Override
    public boolean isUsernameExists(String username) {
        Long count = em.createQuery(
                "SELECT COUNT(u) FROM Users u WHERE u.userName = :uname",
                Long.class
        )
                .setParameter("uname", username)
                .getSingleResult();

        return count > 0;
    }
@Override
public boolean isEmailExists(String email) {
    Long count = em.createQuery(
        "SELECT COUNT(u) FROM Users u WHERE u.email = :email",
        Long.class
    )
    .setParameter("email", email)
    .getSingleResult();

    return count > 0;
}
//for admin

@Override
public List<Recipes> getRecipesByUser(int userId) {
    return em.createQuery("SELECT r FROM Recipes r WHERE r.userId.userId = :uid ORDER BY r.createdAt DESC", Recipes.class)
             .setParameter("uid", userId)
             .getResultList();
}

@Override
public List<Recipes> getLikedRecipes(int userId) {
    return em.createQuery(
        "SELECT r FROM Recipes r WHERE r.recipeId IN " +
        "(SELECT l.likesPK.recipeId FROM Likes l WHERE l.likesPK.userId = :uid)",
        Recipes.class)
        .setParameter("uid", userId)
        .getResultList();
}



}
