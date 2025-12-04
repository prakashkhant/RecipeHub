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
}
