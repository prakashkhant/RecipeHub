package cdi;

import Entity.Recipes;
import ejb.UserBeanLocal;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class UserDashboardBean {

    @Inject
    private LoginBean loginBean;

    @EJB
    private UserBeanLocal userBean;

    // Always fetch updated recipes from DB
    public List<Recipes> getUserRecipes() {
        if (loginBean.getLoggedUser() != null) {
            return userBean.getUserRecipes(loginBean.getLoggedUser().getUserId());
        }
        return List.of();
    }

    public int getUserRecipesCount() {
        return getUserRecipes().size();
    }

    public int getLikedRecipesCount() {
        return loginBean.getLoggedUser() != null ?
                loginBean.getLoggedUser().getLikesCollection().size() : 0;
    }

    public String deleteRecipe(int recipeId) {
        userBean.deleteRecipe(recipeId);
        return "/user/userDashboard?faces-redirect=true";
    }
}
