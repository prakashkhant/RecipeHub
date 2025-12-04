/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author praka
 */
@Entity
@Table(name = "ingredients")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ingredients.findAll", query = "SELECT i FROM Ingredients i"),
    @NamedQuery(name = "Ingredients.findByIngredientId", query = "SELECT i FROM Ingredients i WHERE i.ingredientId = :ingredientId"),
    @NamedQuery(name = "Ingredients.findByName", query = "SELECT i FROM Ingredients i WHERE i.name = :name"),
    @NamedQuery(name = "Ingredients.findByUnit", query = "SELECT i FROM Ingredients i WHERE i.unit = :unit")})
public class Ingredients implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ingredient_id")
    private Integer ingredientId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Size(max = 20)
    @Column(name = "unit")
    private String unit;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ingredients")
    private Collection<RecipeIngredients> recipeIngredientsCollection;

    public Ingredients() {
    }

    public Ingredients(Integer ingredientId) {
        this.ingredientId = ingredientId;
    }

    public Ingredients(Integer ingredientId, String name) {
        this.ingredientId = ingredientId;
        this.name = name;
    }

    public Integer getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Integer ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @XmlTransient
    public Collection<RecipeIngredients> getRecipeIngredientsCollection() {
        return recipeIngredientsCollection;
    }

    public void setRecipeIngredientsCollection(Collection<RecipeIngredients> recipeIngredientsCollection) {
        this.recipeIngredientsCollection = recipeIngredientsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ingredientId != null ? ingredientId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ingredients)) {
            return false;
        }
        Ingredients other = (Ingredients) object;
        if ((this.ingredientId == null && other.ingredientId != null) || (this.ingredientId != null && !this.ingredientId.equals(other.ingredientId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Ingredients[ ingredientId=" + ingredientId + " ]";
    }
    
}
