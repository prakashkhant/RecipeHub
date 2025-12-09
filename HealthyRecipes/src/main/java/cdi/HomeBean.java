package cdi;

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
    private String selectedCategory;

public void loadCategory() {
    selectedCategory = FacesContext.getCurrentInstance()
            .getExternalContext()
            .getRequestParameterMap()
            .get("cat");
}

public List<Recipes> getRecipesByCategory() {
    if (selectedCategory == null || selectedCategory.isEmpty()) {
        return getAllRecipes(); // Show all
    }
return getAllRecipes().stream()
        .filter(r -> r.getCategory().equalsIgnoreCase(selectedCategory))
        .toList();
}

public String getSelectedCategory() {
    return selectedCategory;
}

    public List<Recipes> getRecentRecipes() {
        List<Recipes> list = userBean.getAllRecipesDESC();
        return list.size() > 8 ? list.subList(0, 8) : list;
    }
    
    public List<String> getCategoryList() {
        return userBean.getAllCategories();
    }
    public List<Recipes> getAllRecipes() {
    return userBean.getAllRecipes();
}

}
