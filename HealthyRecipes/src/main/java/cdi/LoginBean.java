/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdi;

import Entity.Users;
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


public String login() {
    loggedUser = userBean.login(username, password);
    System.out.println("Trying login: " + loggedUser);

    if (loggedUser != null) {
        if (loggedUser.getRole().equalsIgnoreCase("admin")) {
            return "admin/adminDashboard.jsf?faces-redirect=true";
        } else {
            return "user/userDashboard.jsf?faces-redirect=true";
        }
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
}
