package cdi;

import Entity.Categories;
import ejb.AdminBeanLocal;
import ejb.UserBeanLocal;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named("categoryBean")
@SessionScoped
public class CategoryBean implements Serializable {

    @EJB
    private AdminBeanLocal adminBean;

    @EJB
    private UserBeanLocal userBean;

    @Inject
    private LoginBean loginBean;

    private List<Categories> allCategories = new ArrayList<>();

    private String newCategoryName;
    private String newDescription;

    private int page = 0;
    private final int pageSize = 5;

    @PostConstruct
    public void init() {
        loadCategories();
    }

    public void loadCategories() {
        allCategories = adminBean.getAllCategories();
    }

    // Pagination
    public List<Categories> getPaginatedCategories() {
        int start = page * pageSize;
        int end = Math.min(start + pageSize, allCategories.size());
        return allCategories.subList(start, end);
    }

    public void nextPage() {
        if ((page + 1) * pageSize < allCategories.size()) {
            page++;
        }
    }

    public void previousPage() {
        if (page > 0) {
            page--;
        }
    }

    public int getPage() {
        return page + 1;
    }

    public int getTotalCategories() {
        return allCategories.size();
    }

    // ADD CATEGORY
 public void addCategory() {
    if (newCategoryName == null || newCategoryName.trim().isEmpty()) {
        addMessage("Category name is required!", FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        return;
    }

    try {
        Categories c = new Categories();
        c.setCategoryName(newCategoryName.trim());
        c.setDescription(newDescription);
        c.setCreatedAt(new Date());

        userBean.addCategory(c);

        adminBean.logActivity(loginBean.getLoggedUser(),
                "Added category: " + newCategoryName);

        addMessage("Category added successfully!", FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

        newCategoryName = "";
        newDescription = "";

        loadCategories();

    } catch (Exception e) {
        addMessage("Failed to add category!", FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }
}

    // UPDATE CATEGORY
public void updateCategory(Categories c) {
    try {
        userBean.updateCategory(c);

        adminBean.logActivity(loginBean.getLoggedUser(),
                "Updated category: " + c.getCategoryName());

        addMessage("Category updated!", FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

        loadCategories();

    } catch (Exception e) {
        addMessage("Failed to update category!", FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }
}

    // DELETE CATEGORY
public void deleteCategory(int id) {
    try {
        userBean.deleteCategory(id);

        adminBean.logActivity(loginBean.getLoggedUser(),
                "Deleted category ID: " + id);

        addMessage("Category deleted!", FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

        loadCategories();

    } catch (Exception e) {
        addMessage("Failed to delete category!", FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }
}

    // Helper for Messages
    private void addMessage(String msg, FacesMessage.Severity type) {
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(type, msg, null));
    }

    // GETTERS & SETTERS
    public String getNewCategoryName() {
        return newCategoryName;
    }

    public void setNewCategoryName(String newCategoryName) {
        this.newCategoryName = newCategoryName;
    }

    public String getNewDescription() {
        return newDescription;
    }

    public void setNewDescription(String newDescription) {
        this.newDescription = newDescription;
    }
}
