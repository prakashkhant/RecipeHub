package cdi;

import Entity.ActivityLog;
import Entity.Recipes;
import Entity.Users;
import ejb.AdminBeanLocal;
import ejb.UserBeanLocal;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import jakarta.inject.Inject;

@Named
@SessionScoped
public class AdminDashboardBean implements Serializable {

    @Inject
    private LoginBean loginBean;
    @EJB
    private AdminBeanLocal adminBean;

    @EJB
    private UserBeanLocal userBean;

    private int userPage = 0;
    private int recipePage = 0;
    private final int pageSize = 10;

    public List<Users> getAllUsersPaginated() {
        List<Users> all = userBean.getAllUsers();
        int start = userPage * pageSize;
        int end = Math.min(start + pageSize, all.size());
        return all.subList(start, end);
    }

    public List<Recipes> getAllRecipesPaginated() {
        List<Recipes> all = userBean.getAllRecipes();
        int start = recipePage * pageSize;
        int end = Math.min(start + pageSize, all.size());
        return all.subList(start, end);
    }

    public void nextUserPage() {
        if ((userPage + 1) * pageSize < userBean.getAllUsers().size()) {
            userPage++;
        }
    }

    public void previousUserPage() {
        if (userPage > 0) {
            userPage--;
        }
    }

    public void nextRecipePage() {
        if ((recipePage + 1) * pageSize < userBean.getAllRecipes().size()) {
            recipePage++;
        }
    }

    public void previousRecipePage() {
        if (recipePage > 0) {
            recipePage--;
        }
    }

    public int getUserPage() {
        return userPage + 1;
    }

    public int getRecipePage() {
        return recipePage + 1;
    }

    public String deleteUser(Integer userId) {
        Users deletedUser = userBean.getUserById(userId);

        adminBean.logActivity(loginBean.getLoggedUser(),
                "Deleted user: " + deletedUser.getFullName());

        userBean.deleteUser(userId);
        return "/admin/adminDashboard?faces-redirect=true";
    }

public String deleteRecipe(Integer recipeId) {
    Recipes recipe = userBean.getRecipeById(recipeId);

    if (recipe != null) {
        adminBean.logActivity(loginBean.getLoggedUser(),
                "Deleted recipe: " + recipe.getTitle());
    }

    userBean.deleteRecipe(recipeId);
    return "/admin/adminDashboard?faces-redirect=true";
}


    public int getTotalUsers() {
        return userBean.getAllUsers().size();
    }

    public int getTotalRecipes() {
        return userBean.getAllRecipes().size();
    }

    public String toggleUserRole(Integer userId) {

// Prevent changing own role
        if (loginBean.getLoggedUser() != null
                && loginBean.getLoggedUser().getUserId().equals(userId)) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "You cannot change your own role!", null));

            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            return "/admin/manageUsers?faces-redirect=true";
        }

        Users user = userBean.getUserById(userId);

        if (user != null) {
            if ("admin".equalsIgnoreCase(user.getRole())) {
                user.setRole("USER");
            } else {
                user.setRole("ADMIN");
            }
            userBean.updateUser(user);
            adminBean.logActivity(loginBean.getLoggedUser(),
        "Changed role of: " + user.getFullName() +
        " to " + user.getRole());

        }

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "User role updated!", null));

        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        return "/admin/manageUsers?faces-redirect=true";
    }

    public List<ActivityLog> getAllActivities() {
        System.out.println("Activities count: " + adminBean.getAllActivities().size());

        return adminBean.getAllActivities();
        
    }
    private String activityFilter = "ALL"; // ALL, TODAY, WEEK, MONTH
    private Integer filterUserId; // For specific user filter

    public String getActivityFilter() {
        return activityFilter;
    }

    public void setActivityFilter(String activityFilter) {
        this.activityFilter = activityFilter;
    }

    public Integer getFilterUserId() {
        return filterUserId;
    }

    public void setFilterUserId(Integer filterUserId) {
        this.filterUserId = filterUserId;
    }

}
