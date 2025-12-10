package cdi;

import Entity.Categories;
import Entity.Recipes;
import ejb.UserBeanLocal;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.util.List;

@Named("homeBean")
@RequestScoped
public class HomeBean {

    @EJB
    private UserBeanLocal userBean;
//    private String selectedCategory;
private Categories selectedCategory;
private Integer selectedCategoryId; 

public void loadCategory() {
    String idStr = FacesContext.getCurrentInstance()
            .getExternalContext()
            .getRequestParameterMap().get("cat");

    if (idStr != null && !idStr.isEmpty()) {
selectedCategoryId = Integer.valueOf(idStr);
selectedCategory = userBean.getCategoryById(selectedCategoryId);    } else {
        selectedCategory = null; // All categories
    }
}

public List<Recipes> getRecipesByCategory() {
    if (selectedCategory == null) {
        return getAllRecipes();    // Show all recipes
    }

    return getAllRecipes()
        .stream()
        .filter(r -> r.getCategoryId() != null &&
                     r.getCategoryId().getCategoryId().equals(selectedCategory.getCategoryId()))
        .toList();

}
    public Categories getSelectedCategory() {
        return selectedCategory;
    }

    public List<Recipes> getRecentRecipes() {
        List<Recipes> list = userBean.getAllRecipesDESC();
        return list.size() > 8 ? list.subList(0, 8) : list;
    }

    public List<Categories> getCategoryList() {
        return userBean.getAllCategories();
    }

    public List<Recipes> getAllRecipes() {
        return userBean.getAllRecipes();
    }

public Integer getSelectedCategoryId() {
    return selectedCategoryId;
}

public void setSelectedCategoryId(Integer selectedCategoryId) {
    this.selectedCategoryId = selectedCategoryId;
}

}
