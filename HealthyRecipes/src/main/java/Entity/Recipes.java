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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author praka
 */
@Entity
@Table(name = "recipes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recipes.findAll", query = "SELECT r FROM Recipes r"),
    @NamedQuery(name = "Recipes.findAllDesc", query = "SELECT r FROM Recipes r ORDER BY r.createdAt DESC"),
    @NamedQuery(name = "Recipes.findByRecipeId", query = "SELECT r FROM Recipes r WHERE r.recipeId = :recipeId"),
    @NamedQuery(name = "Recipes.findByTitle", query = "SELECT r FROM Recipes r WHERE r.title = :title"),
    @NamedQuery(name = "Recipes.findByCategory", query = "SELECT r FROM Recipes r WHERE r.category = :category"),
    @NamedQuery(name = "Recipes.findByDifficulty", query = "SELECT r FROM Recipes r WHERE r.difficulty = :difficulty"),
    @NamedQuery(name = "Recipes.findByImageUrl", query = "SELECT r FROM Recipes r WHERE r.imageUrl = :imageUrl"),
    @NamedQuery(name = "Recipes.findByVideoUrl", query = "SELECT r FROM Recipes r WHERE r.videoUrl = :videoUrl"),
    @NamedQuery(name = "Recipes.findByCreatedAt", query = "SELECT r FROM Recipes r WHERE r.createdAt = :createdAt")})
public class Recipes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "recipe_id")
    private Integer recipeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "title")
    private String title;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Size(max = 50)
    @Column(name = "category")
    private String category;
    @Lob
    @Column(name = "steps")
    private String steps;

    @Size(max = 6)
    @Column(name = "difficulty")
    private String difficulty;
    @Size(max = 255)
    @Column(name = "image_url")
    private String imageUrl;
    @Size(max = 255)
    @Column(name = "video_url")
    private String videoUrl;

    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipeId")
    private Collection<Comments> commentsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipes")
    private Collection<RecipeIngredients> recipeIngredientsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipeId")
    private Collection<Ratings> ratingsCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipes")
    private Collection<Likes> likesCollection;

    public Recipes() {
    }

    public Recipes(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public Recipes(Integer recipeId, String title, Date createdAt) {
        this.recipeId = recipeId;
        this.title = title;
        this.createdAt = createdAt;
    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    @XmlTransient
    public Collection<Comments> getCommentsCollection() {
        return commentsCollection;
    }

    public void setCommentsCollection(Collection<Comments> commentsCollection) {
        this.commentsCollection = commentsCollection;
    }

    @XmlTransient
    public Collection<RecipeIngredients> getRecipeIngredientsCollection() {
        return recipeIngredientsCollection;
    }

    public void setRecipeIngredientsCollection(Collection<RecipeIngredients> recipeIngredientsCollection) {
        this.recipeIngredientsCollection = recipeIngredientsCollection;
    }

    @XmlTransient
    public Collection<Ratings> getRatingsCollection() {
        return ratingsCollection;
    }

    public void setRatingsCollection(Collection<Ratings> ratingsCollection) {
        this.ratingsCollection = ratingsCollection;
    }

    @XmlTransient
    public Collection<Likes> getLikesCollection() {
        return likesCollection;
    }

    public void setLikesCollection(Collection<Likes> likesCollection) {
        this.likesCollection = likesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recipeId != null ? recipeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recipes)) {
            return false;
        }
        Recipes other = (Recipes) object;
        if ((this.recipeId == null && other.recipeId != null) || (this.recipeId != null && !this.recipeId.equals(other.recipeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Recipes[ recipeId=" + recipeId + " ]";
    }

}
