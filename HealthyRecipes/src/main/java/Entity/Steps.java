/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author praka
 */
@Entity
@Table(name = "steps")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Steps.findAll", query = "SELECT s FROM Steps s"),
    @NamedQuery(name = "Steps.findByStepId", query = "SELECT s FROM Steps s WHERE s.stepId = :stepId"),
    @NamedQuery(name = "Steps.findByStepNo", query = "SELECT s FROM Steps s WHERE s.stepNo = :stepNo")})
public class Steps implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "step_id")
    private Integer stepId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "step_no")
    private int stepNo;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "instruction")
    private String instruction;
    @JoinColumn(name = "recipe_id", referencedColumnName = "recipe_id")
    @ManyToOne(optional = false)
    private Recipes recipeId;

    public Steps() {
    }

    public Steps(Integer stepId) {
        this.stepId = stepId;
    }

    public Steps(Integer stepId, int stepNo, String instruction) {
        this.stepId = stepId;
        this.stepNo = stepNo;
        this.instruction = instruction;
    }

    public Integer getStepId() {
        return stepId;
    }

    public void setStepId(Integer stepId) {
        this.stepId = stepId;
    }

    public int getStepNo() {
        return stepNo;
    }

    public void setStepNo(int stepNo) {
        this.stepNo = stepNo;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
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
        hash += (stepId != null ? stepId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Steps)) {
            return false;
        }
        Steps other = (Steps) object;
        if ((this.stepId == null && other.stepId != null) || (this.stepId != null && !this.stepId.equals(other.stepId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Steps[ stepId=" + stepId + " ]";
    }
    
}
