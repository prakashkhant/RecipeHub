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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author praka
 */
@Entity
@Table(name = "likes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Likes.findAll", query = "SELECT l FROM Likes l"),
    @NamedQuery(name = "Likes.findByRecipeId", query = "SELECT l FROM Likes l WHERE l.likesPK.recipeId = :recipeId"),
    @NamedQuery(name = "Likes.findByUserId", query = "SELECT l FROM Likes l WHERE l.likesPK.userId = :userId"),
    @NamedQuery(name = "Likes.findByLikedAt", query = "SELECT l FROM Likes l WHERE l.likedAt = :likedAt")})
public class Likes implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LikesPK likesPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "liked_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date likedAt;
    @JoinColumn(name = "recipe_id", referencedColumnName = "recipe_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Recipes recipes;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public Likes() {
    }

    public Likes(LikesPK likesPK) {
        this.likesPK = likesPK;
    }

    public Likes(LikesPK likesPK, Date likedAt) {
        this.likesPK = likesPK;
        this.likedAt = likedAt;
    }

    public Likes(int recipeId, int userId) {
        this.likesPK = new LikesPK(recipeId, userId);
    }

    public LikesPK getLikesPK() {
        return likesPK;
    }

    public void setLikesPK(LikesPK likesPK) {
        this.likesPK = likesPK;
    }

    public Date getLikedAt() {
        return likedAt;
    }

    public void setLikedAt(Date likedAt) {
        this.likedAt = likedAt;
    }

    public Recipes getRecipes() {
        return recipes;
    }

    public void setRecipes(Recipes recipes) {
        this.recipes = recipes;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (likesPK != null ? likesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Likes)) {
            return false;
        }
        Likes other = (Likes) object;
        if ((this.likesPK == null && other.likesPK != null) || (this.likesPK != null && !this.likesPK.equals(other.likesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Likes[ likesPK=" + likesPK + " ]";
    }
    
}
