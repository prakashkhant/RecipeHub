package cdi;

import Entity.Comments;
import Entity.Recipes;
import ejb.AdminBeanLocal;
import ejb.UserBeanLocal;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.Part;
import java.io.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Named(value = "recipeBean")
@SessionScoped
public class RecipeBean implements Serializable {

    private String title;
    private String description;
    private String steps;
    private String category;
    private String difficulty;
    private Part imageFile;
    private String videoUrl;
    private Integer editRecipeId;
    private boolean editing = false;

    @EJB
    private UserBeanLocal userBean;

    @Inject
    private LoginBean loginBean;
    @EJB
    private AdminBeanLocal adminBean;

    public RecipeBean() {
    }

    public String saveRecipe() {
        try {
            if (loginBean.getLoggedUser() == null) {
                addMessage("Login session expired!", FacesMessage.SEVERITY_ERROR);
                return "/login?faces-redirect=true";
            }

            // Validation
            if (title == null || title.trim().isEmpty()
                    || category == null || category.isEmpty()
                    || difficulty == null || difficulty.isEmpty()) {

                addMessage("Please fill all required fields!", FacesMessage.SEVERITY_ERROR);
                return null;
            }

            Recipes r = new Recipes();
            r.setTitle(title);
            r.setDescription(description);
            r.setSteps(steps);
            r.setCategory(category);
            r.setDifficulty(difficulty);
            r.setVideoUrl(videoUrl);
            r.setCreatedAt(new Date());
            r.setUserId(loginBean.getLoggedUser());

            // IMAGE UPLOAD
            if (imageFile != null) {
                String fileName = System.currentTimeMillis() + "_" + imageFile.getSubmittedFileName();
                r.setImageUrl(fileName);

                String uploadPath = FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .getRealPath("/resources/images/");

                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                try (InputStream input = imageFile.getInputStream(); FileOutputStream output = new FileOutputStream(new File(uploadDir, fileName))) {

                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = input.read(buffer)) != -1) {
                        output.write(buffer, 0, bytesRead);
                    }
                }
            }

            userBean.addRecipe(r);
            adminBean.logActivity(loginBean.getLoggedUser(),
                    "Added a recipe: " + r.getTitle());

            clearForm();
            if (loginBean.getLoggedUser() != null
                    && "admin".equalsIgnoreCase(loginBean.getLoggedUser().getRole())) {
                return "/admin/adminDashboard?faces-redirect=true";
            }
            return "/user/userDashboard?faces-redirect=true";

        } catch (Exception e) {
            addMessage("Error saving recipe! ❌", FacesMessage.SEVERITY_ERROR);
            return null;
        }
    }

    private void addMessage(String text, FacesMessage.Severity type) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(type, text, null));
    }

    public String editRecipe(int recipeId) {
        Recipes r = userBean.getRecipeById(recipeId);

        if (r != null) {
            this.editRecipeId = recipeId;
            this.title = r.getTitle();
            this.description = r.getDescription();
            this.steps = r.getSteps();
            this.category = r.getCategory();
            this.difficulty = r.getDifficulty();
            this.videoUrl = r.getVideoUrl();
            this.editing = true;
        }

        return "/user/updateRecipe?faces-redirect=true";
    }

    public String updateRecipe() {
        try {
            Recipes r = userBean.getRecipeById(editRecipeId);

            r.setTitle(title);
            r.setDescription(description);
            r.setSteps(steps);
            r.setCategory(category);
            r.setDifficulty(difficulty);
            r.setVideoUrl(videoUrl);

            if (imageFile != null && imageFile.getSubmittedFileName() != null
                    && !imageFile.getSubmittedFileName().isEmpty()) {

                String fileName = System.currentTimeMillis() + "_" + imageFile.getSubmittedFileName();
                r.setImageUrl(fileName);

                String uploadPath = FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .getRealPath("/resources/images/");

                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                try (InputStream input = imageFile.getInputStream(); FileOutputStream output = new FileOutputStream(new File(uploadDir, fileName))) {

                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = input.read(buffer)) != -1) {
                        output.write(buffer, 0, bytesRead);
                    }
                }
            }

            userBean.updateRecipe(r);
            adminBean.logActivity(loginBean.getLoggedUser(),
                    "Updated recipe: " + r.getTitle());

            addMessage("Recipe Updated Successfully! 🎉", FacesMessage.SEVERITY_INFO);
            editing = false;
            clearForm();

            if (loginBean.getLoggedUser() != null
                    && "admin".equalsIgnoreCase(loginBean.getLoggedUser().getRole())) {
                return "/admin/adminDashboard?faces-redirect=true";
            }

            return "/user/userDashboard?faces-redirect=true";

        } catch (Exception e) {
            addMessage("Failed to update recipe! ❌", FacesMessage.SEVERITY_ERROR);
            return null;
        }
    }

    public boolean isEditing() {
        return editing;
    }

    public void clearForm() {
        title = null;
        description = null;
        steps = null;
        category = null;
        difficulty = null;
        imageFile = null;
        videoUrl = null;
    }
    private Recipes selectedRecipe;

    public void loadRecipe() {
        try {
            String idStr = FacesContext.getCurrentInstance().getExternalContext()
                    .getRequestParameterMap().get("id");
            if (idStr != null) {
                int id = Integer.parseInt(idStr);
                selectedRecipe = userBean.getRecipeById(id);
            }
        } catch (Exception e) {
            System.out.println("Recipe load error: " + e.getMessage());
        }
    }

    public Recipes getSelectedRecipe() {
        return selectedRecipe;
    }

    // GETTERS & SETTERS
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Part getImageFile() {
        return imageFile;
    }

    public void setImageFile(Part imageFile) {
        this.imageFile = imageFile;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

//    admin methods
    public String adminEditRecipe(int recipeId) {
        Recipes r = userBean.getRecipeById(recipeId);

        if (r != null) {
            this.editRecipeId = recipeId;
            this.title = r.getTitle();
            this.description = r.getDescription();
            this.steps = r.getSteps();
            this.category = r.getCategory();
            this.difficulty = r.getDifficulty();
            this.videoUrl = r.getVideoUrl();
            this.editing = true;
        }

        return "/admin/updateRecipe?faces-redirect=true";
    }

//    likes / deslikes
    public void toggleLike(int recipeId) {
        if (loginBean.getLoggedUser() == null) {
            return; // Not logged ⇒ Ignore
        }

        userBean.toggleLike(recipeId, loginBean.getLoggedUser().getUserId());
        boolean likedBefore = userBean.isRecipeLiked(recipeId, loginBean.getLoggedUser().getUserId());

        String action = likedBefore ? "Liked" : "Unliked";
        Recipes recipe = userBean.getRecipeById(recipeId);
        adminBean.logActivity(loginBean.getLoggedUser(),
                action + " recipe: " + recipe.getTitle());

    }

    public boolean isLiked(int recipeId) {
        if (loginBean.getLoggedUser() == null) {
            return false;
        }
        return userBean.isRecipeLiked(recipeId, loginBean.getLoggedUser().getUserId());
    }

    public long getLikeCount(int recipeId) {
        return userBean.getLikeCount(recipeId);
    }

    public void removeLike(int recipeId) {
        if (loginBean.getLoggedUser() != null) {
            userBean.removeLike(recipeId, loginBean.getLoggedUser().getUserId());
        }
    }
//comment section

    private String commentText;

    public void addComment() {
        if (loginBean.getLoggedUser() == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Login required!", null));
            return;
        }

        userBean.addComment(selectedRecipe.getRecipeId(),
                loginBean.getLoggedUser().getUserId(),
                commentText);
        adminBean.logActivity(loginBean.getLoggedUser(),
                "Commented on recipe: " + selectedRecipe.getTitle());

        commentText = ""; // clear input
    }

    public List<Comments> getComments() {
        if (selectedRecipe != null) {
            return userBean.getCommentsByRecipe(selectedRecipe.getRecipeId());
        }
        return List.of();
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

}
