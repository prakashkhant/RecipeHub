/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EntityManager;

import jakarta.persistence.Basic;
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
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author praka
 */
@Entity
@Table(name = "admin_actions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdminActions.findAll", query = "SELECT a FROM AdminActions a"),
    @NamedQuery(name = "AdminActions.findByActionId", query = "SELECT a FROM AdminActions a WHERE a.actionId = :actionId"),
    @NamedQuery(name = "AdminActions.findByActionType", query = "SELECT a FROM AdminActions a WHERE a.actionType = :actionType"),
    @NamedQuery(name = "AdminActions.findByCreatedAt", query = "SELECT a FROM AdminActions a WHERE a.createdAt = :createdAt")})
public class AdminActions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "action_id")
    private Integer actionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "action_type")
    private String actionType;
    @Lob
    @Size(max = 65535)
    @Column(name = "reason")
    private String reason;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "admin_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users adminId;
    @JoinColumn(name = "recipe_id", referencedColumnName = "recipe_id")
    @ManyToOne
    private Recipes recipeId;

    public AdminActions() {
    }

    public AdminActions(Integer actionId) {
        this.actionId = actionId;
    }

    public AdminActions(Integer actionId, String actionType) {
        this.actionId = actionId;
        this.actionType = actionType;
    }

    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Users getAdminId() {
        return adminId;
    }

    public void setAdminId(Users adminId) {
        this.adminId = adminId;
    }

    public Recipes getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Recipes recipeId) {
        this.recipeId = recipeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (actionId != null ? actionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdminActions)) {
            return false;
        }
        AdminActions other = (AdminActions) object;
        if ((this.actionId == null && other.actionId != null) || (this.actionId != null && !this.actionId.equals(other.actionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.recipehub.AdminActions[ actionId=" + actionId + " ]";
    }
    
}
