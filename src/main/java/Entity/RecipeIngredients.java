/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author praka
 */
@Entity
@Table(name = "recipe_ingredients")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RecipeIngredients.findAll", query = "SELECT r FROM RecipeIngredients r"),
    @NamedQuery(name = "RecipeIngredients.findByRecipeId", query = "SELECT r FROM RecipeIngredients r WHERE r.recipeIngredientsPK.recipeId = :recipeId"),
    @NamedQuery(name = "RecipeIngredients.findByIngredientId", query = "SELECT r FROM RecipeIngredients r WHERE r.recipeIngredientsPK.ingredientId = :ingredientId"),
    @NamedQuery(name = "RecipeIngredients.findByQuantity", query = "SELECT r FROM RecipeIngredients r WHERE r.quantity = :quantity"),
    @NamedQuery(name = "RecipeIngredients.findByUnitOverride", query = "SELECT r FROM RecipeIngredients r WHERE r.unitOverride = :unitOverride")})
public class RecipeIngredients implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RecipeIngredientsPK recipeIngredientsPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private BigDecimal quantity;
    @Size(max = 20)
    @Column(name = "unit_override")
    private String unitOverride;
    @JoinColumn(name = "recipe_id", referencedColumnName = "recipe_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Recipes recipes;
    @JoinColumn(name = "ingredient_id", referencedColumnName = "ingredient_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ingredients ingredients;

    public RecipeIngredients() {
    }

    public RecipeIngredients(RecipeIngredientsPK recipeIngredientsPK) {
        this.recipeIngredientsPK = recipeIngredientsPK;
    }

    public RecipeIngredients(RecipeIngredientsPK recipeIngredientsPK, BigDecimal quantity) {
        this.recipeIngredientsPK = recipeIngredientsPK;
        this.quantity = quantity;
    }

    public RecipeIngredients(int recipeId, int ingredientId) {
        this.recipeIngredientsPK = new RecipeIngredientsPK(recipeId, ingredientId);
    }

    public RecipeIngredientsPK getRecipeIngredientsPK() {
        return recipeIngredientsPK;
    }

    public void setRecipeIngredientsPK(RecipeIngredientsPK recipeIngredientsPK) {
        this.recipeIngredientsPK = recipeIngredientsPK;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getUnitOverride() {
        return unitOverride;
    }

    public void setUnitOverride(String unitOverride) {
        this.unitOverride = unitOverride;
    }

    public Recipes getRecipes() {
        return recipes;
    }

    public void setRecipes(Recipes recipes) {
        this.recipes = recipes;
    }

    public Ingredients getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredients ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recipeIngredientsPK != null ? recipeIngredientsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecipeIngredients)) {
            return false;
        }
        RecipeIngredients other = (RecipeIngredients) object;
        if ((this.recipeIngredientsPK == null && other.recipeIngredientsPK != null) || (this.recipeIngredientsPK != null && !this.recipeIngredientsPK.equals(other.recipeIngredientsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.RecipeIngredients[ recipeIngredientsPK=" + recipeIngredientsPK + " ]";
    }
    
}
