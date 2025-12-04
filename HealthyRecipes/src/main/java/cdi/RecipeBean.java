package cdi;

import Entity.Recipes;
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

    public RecipeBean() {}

    public String saveRecipe() {
        try {
            if (loginBean.getLoggedUser() == null) {
                addMessage("Login session expired!", FacesMessage.SEVERITY_ERROR);
                return "/login?faces-redirect=true";
            }

            // Validation
            if (title == null || title.trim().isEmpty() ||
                category == null || category.isEmpty() ||
                difficulty == null || difficulty.isEmpty()) {

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
                if (!uploadDir.exists()) uploadDir.mkdirs();

                try (InputStream input = imageFile.getInputStream();
                     FileOutputStream output = new FileOutputStream(new File(uploadDir, fileName))) {

                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = input.read(buffer)) != -1) {
                        output.write(buffer, 0, bytesRead);
                    }
                }
            }

            userBean.addRecipe(r);
            clearForm();

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

   if (imageFile != null && imageFile.getSubmittedFileName() != null &&
        !imageFile.getSubmittedFileName().isEmpty()) {

    String fileName = System.currentTimeMillis() + "_" + imageFile.getSubmittedFileName();
    r.setImageUrl(fileName);

    String uploadPath = FacesContext.getCurrentInstance()
            .getExternalContext()
            .getRealPath("/resources/images/");

    File uploadDir = new File(uploadPath);
    if (!uploadDir.exists()) uploadDir.mkdirs();

    try (InputStream input = imageFile.getInputStream();
         FileOutputStream output = new FileOutputStream(new File(uploadDir, fileName))) {

        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
    }
}


        userBean.updateRecipe(r);

        addMessage("Recipe Updated Successfully! 🎉", FacesMessage.SEVERITY_INFO);
        editing = false;
        clearForm();

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

public Recipes getSelectedRecipe() { return selectedRecipe; }


    // GETTERS & SETTERS
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getSteps() { return steps; }
    public void setSteps(String steps) { this.steps = steps; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }
    public Part getImageFile() { return imageFile; }
    public void setImageFile(Part imageFile) { this.imageFile = imageFile; }
    public String getVideoUrl() { return videoUrl; }
    public void setVideoUrl(String videoUrl) { this.videoUrl = videoUrl; }
}
