package cdi;

import Entity.Comments;
import Entity.Likes;
import Entity.Recipes;
import ejb.UserBeanLocal;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
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
     public List<Recipes> getLikedRecipes() {
        List<Likes> likes = userBean.getUserLikes(loginBean.getLoggedUser().getUserId());
        return likes.stream()
                .map(l -> userBean.getRecipeByLike(l.getLikesPK().getRecipeId()))
                .toList();
    }
     public List<Comments> getUserComments() {
    if (loginBean.getLoggedUser() != null) {
        return userBean.getUserComments(loginBean.getLoggedUser().getUserId());
    }
    return List.of();
}

public String deleteComment(int commentId) {
    userBean.deleteComment(commentId);
    return "/user/activity?faces-redirect=true";
}

}
