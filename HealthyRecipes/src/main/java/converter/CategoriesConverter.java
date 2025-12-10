package converter;

import Entity.Categories;
import ejb.UserBeanLocal;
import jakarta.ejb.EJB;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter(value = "categoryConverter", managed = true)
public class CategoriesConverter implements Converter {

    @EJB
    private UserBeanLocal userBean;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return userBean.getCategoryById(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (value == null) {
            return "";
        }

        // When JSF passes Categories object
        if (value instanceof Categories) {
            return String.valueOf(((Categories) value).getCategoryId());
        }

        // When JSF passes Integer (during rendering)
        if (value instanceof Integer) {
            return String.valueOf(value);
        }

        return "";
    }
}
