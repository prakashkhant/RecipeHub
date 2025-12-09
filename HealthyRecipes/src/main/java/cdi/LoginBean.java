/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdi;

import Entity.Users;
import ejb.AdminBeanLocal;
import ejb.UserBeanLocal;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
@SessionScoped
@Named(value = "loginBean")
public class LoginBean implements Serializable {

    private String username;
    private String password;
    private Users loggedUser;

    @EJB
    private UserBeanLocal userBean;
@EJB
private AdminBeanLocal adminBean;


public String login() {
    loggedUser = userBean.login(username, password);
    System.out.println("Trying login: " + loggedUser);

    if (loggedUser != null) {
        adminBean.logActivity(loggedUser, "User logged in");

        return "home.jsf?faces-redirect=true"; // Always go back to home page
    }

    FacesContext.getCurrentInstance().addMessage(
        null,
        new FacesMessage(FacesMessage.SEVERITY_ERROR,
        "Username or Password incorrect!", null)
    );

    return null; // stay on same page, show error
}

public void redirectIfNotLoggedIn() throws IOException {
    if (loggedUser == null) {
        FacesContext.getCurrentInstance()
            .getExternalContext()
            .redirect("login.jsf");
    }
}

public String logout() {
        adminBean.logActivity(loggedUser, "User logged out");

    loggedUser = null;
    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

    return "/home?faces-redirect=true";
}

    // Getters & Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Users getLoggedUser() {
    return loggedUser;
}
    
    
//    for username on home and other pages
  public String goToDashboard() {
    if (loggedUser == null) {
        return "login?faces-redirect=true";
    }
    
    if ("admin".equalsIgnoreCase(loggedUser.getRole())) {
        return "admin/adminDashboard?faces-redirect=true";
    }
    return "user/userDashboard?faces-redirect=true";
}
//    for username on home and other pages
  public String goToMyActivity() {
    if (loggedUser == null) {
        return "login?faces-redirect=true";
    }
    
    if ("admin".equalsIgnoreCase(loggedUser.getRole())) {
        return "admin/adminActivity?faces-redirect=true";
    }
    return "user/activity?faces-redirect=true";
}


}
