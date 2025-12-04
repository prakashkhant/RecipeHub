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
public class LikesPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "recipe_id")
    private int recipeId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private int userId;

    public LikesPK() {
    }

    public LikesPK(int recipeId, int userId) {
        this.recipeId = recipeId;
        this.userId = userId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) recipeId;
        hash += (int) userId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LikesPK)) {
            return false;
        }
        LikesPK other = (LikesPK) object;
        if (this.recipeId != other.recipeId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.LikesPK[ recipeId=" + recipeId + ", userId=" + userId + " ]";
    }
    
}
