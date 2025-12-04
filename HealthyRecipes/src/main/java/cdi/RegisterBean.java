/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */


package cdi;

import Entity.Users;
import ejb.UserBeanLocal;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
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

    public String register() {

        Users u = new Users();
        u.setFullName(fullname);
        u.setUserName(username);
        u.setEmail(email);
        u.setPassword(password);
        u.setRole(role);

        userBean.registerUser(u);
System.out.println(u);
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
