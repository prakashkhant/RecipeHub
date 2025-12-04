package cdi;

import Entity.Recipes;
import ejb.UserBeanLocal;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.List;

@Named("homeBean")
@RequestScoped
public class HomeBean {

    @EJB
    private UserBeanLocal userBean;
    
    public List<Recipes> getRecentRecipes() {
        List<Recipes> list = userBean.getAllRecipes();
        return list.size() > 8 ? list.subList(0, 8) : list;
    }
    
    public List<String> getCategoryList() {
        return userBean.getAllCategories();
    }
    public List<Recipes> getAllRecipes() {
    return userBean.getAllRecipes();
}

}
