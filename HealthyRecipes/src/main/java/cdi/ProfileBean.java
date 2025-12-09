/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdi;

import Entity.Users;
import ejb.UserBeanLocal;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("profileBean")
@SessionScoped
public class ProfileBean implements Serializable {

    @Inject
    private LoginBean loginBean;

    @EJB
    private UserBeanLocal userBean;

    private Users currentUser;

    // Load user details initially
    public Users getCurrentUser() {
        if (currentUser == null) {
            currentUser = loginBean.getLoggedUser();
        }
        return currentUser;
    }
    private String newPassword;
    private String confirmPassword;

    public String updateProfile() {
        try {
            Users user = getCurrentUser();

            // if password fields filled then validate
            if ((newPassword != null && !newPassword.isEmpty())
                    || (confirmPassword != null && !confirmPassword.isEmpty())) {

                if (!newPassword.equals(confirmPassword)) {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    "Passwords do not match!", null));
                    return null;
                }

                user.setPassword(newPassword);
            }

            userBean.updateUser(user);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Profile Updated Successfully! 🎉", null));

            newPassword = "";
            confirmPassword = "";

     

// Keep message through redirect
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getFlash()
                    .setKeepMessages(true);

// Redirect based on role
            if (loginBean.getLoggedUser().getRole().equalsIgnoreCase("admin")) {
                return "/admin/profile?faces-redirect=true";
            }
            return "/user/profile?faces-redirect=true";

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error updating profile! ❌", null));
            return null;
        }
    }

// getters & setters
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

}
