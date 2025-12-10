/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package ejb;

import Entity.ActivityLog;
import Entity.Categories;
import Entity.Users;
import Entity.Recipes;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Stateless
public class AdminBean implements AdminBeanLocal {

    @PersistenceContext(unitName = "healthyPU")
    EntityManager em;

    @Override
    public List<Users> getAllUsers() {
        return em.createNamedQuery("Users.findAll", Users.class).getResultList();
    }

    @Override
    public String deleteUser(int userId) {
        Users u = em.find(Users.class, userId);
        if (u != null) {
            em.remove(u);
            return "User Deleted!";
        }
        return "User Not Found!";
    }

    @Override
    public List<Recipes> getAllRecipes() {
        return em.createNamedQuery("Recipes.findAll", Recipes.class).getResultList();
    }

    @Override
    public String deleteRecipe(int recipeId) {
        Recipes r = em.find(Recipes.class, recipeId);
        if (r != null) {
            em.remove(r);
            return "Recipe Deleted!";
        }
        return "Recipe Not Found!";
    }

    @Override
    public String registerAdmin(Users admin) {
        em.persist(admin);
        return "Admin Registered!";
    }

@Override
public void logActivity(Users user, String activity) {
    ActivityLog log = new ActivityLog();
    log.setUserId(user);
    log.setActivity(activity);
    log.setTimestamp(new Date());
    em.persist(log);
}

@Override
public List<ActivityLog> getAllActivities() {
    return em.createQuery(
        "SELECT a FROM ActivityLog a ORDER BY a.timestamp DESC",
        ActivityLog.class
    ).getResultList();
}
@Override
public List<Categories> getAllCategories() {
    return em.createQuery("SELECT c FROM Categories c ORDER BY c.categoryName", Categories.class)
             .getResultList();
}


// Helpers
    private boolean isToday(Date date) {
        return date.after(new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000));
    }

    private boolean isLast7Days(Date date) {
        return date.after(new Date(System.currentTimeMillis() - 7L * 24 * 60 * 60 * 1000));
    }

    private boolean isThisMonth(Date date) {
        Date monthAgo = new Date(System.currentTimeMillis() - 30L * 24 * 60 * 60 * 1000);
        return date.after(monthAgo);
    }

 
}
