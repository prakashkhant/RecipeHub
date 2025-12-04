/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *
 * @author praka
 */
@Embeddable
public class RecipeIngredientsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "recipe_id")
    private int recipeId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ingredient_id")
    private int ingredientId;

    public RecipeIngredientsPK() {
    }

    public RecipeIngredientsPK(int recipeId, int ingredientId) {
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) recipeId;
        hash += (int) ingredientId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecipeIngredientsPK)) {
            return false;
        }
        RecipeIngredientsPK other = (RecipeIngredientsPK) object;
        if (this.recipeId != other.recipeId) {
            return false;
        }
        if (this.ingredientId != other.ingredientId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.RecipeIngredientsPK[ recipeId=" + recipeId + ", ingredientId=" + ingredientId + " ]";
    }
    
}
