/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */


package cdi;

import Entity.Users;
import ejb.AdminBeanLocal;
import ejb.UserBeanLocal;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;

@Named(value = "registerBean")
@RequestScoped
public class RegisterBean implements Serializable {

    private String fullname;
    private String username;
    private String email;
    private String password;
    private String role = "User"; // Default role

    @EJB
    private UserBeanLocal userBean;
    
    @EJB
private AdminBeanLocal adminBean;

public String register() {

    // Username already exists? ❌
    if (userBean.isUsernameExists(username)) {
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Username already exists! Please choose another.", null));
        return null;
    }

    // Email already exists? ❌
    if (userBean.isEmailExists(email)) {
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Email already registered! Try a different email.", null));
        return null;
    }

    Users u = new Users();
    u.setFullName(fullname);
    u.setUserName(username);
    u.setEmail(email);
    u.setPassword(password);
    u.setRole(role);
    u.setCreatedAt(new java.util.Date());

    userBean.registerUser(u);
adminBean.logActivity(u, "Registered new account");

    return "/login.jsf?faces-redirect=true";
}

    // Getters & Setters
    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
