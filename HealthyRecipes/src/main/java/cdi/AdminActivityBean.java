package cdi;

import Entity.Comments;
import Entity.Recipes;
import ejb.UserBeanLocal;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class AdminActivityBean implements Serializable {

    @Inject
    private LoginBean loginBean;

    @EJB
    private UserBeanLocal userBean;

    public List<Recipes> getAdminRecipes() {
        return userBean.getRecipesByUser(loginBean.getLoggedUser().getUserId());
    }

    public List<Recipes> getAdminLikedRecipes() {
        return userBean.getLikedRecipes(loginBean.getLoggedUser().getUserId());
    }

    public List<Comments> getAdminComments() {
        return userBean.getUserComments(loginBean.getLoggedUser().getUserId());
    }

    public String deleteComment(int commentId) {
        userBean.deleteComment(commentId);
        return "/admin/adminActivity?faces-redirect=true";
    }

    public void removeLike(int recipeId) {
        userBean.removeLike(recipeId, loginBean.getLoggedUser().getUserId());
    }

public void redirectIfNotMainAdmin() {
    try {
        // If user not logged in → block
        if (loginBean.getLoggedUser() == null) {
            FacesContext.getCurrentInstance()
                .getExternalContext()
                .redirect("/HealthyRecipes/login.jsf");
            return;
        }

        // If username is NOT "admin" → block
        if (!loginBean.getLoggedUser().getUserName().equalsIgnoreCase("admin")) {
            FacesContext.getCurrentInstance()
                .getExternalContext()
                .redirect("/HealthyRecipes/admin/accessDenied.jsf");
            return;
        }

    } catch (Exception e) {
        System.out.println("Redirect error: " + e.getMessage());
    }
}

}
