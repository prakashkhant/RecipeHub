package cdi;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.faces.context.FacesContext;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Inject;


import java.io.Serializable;
import java.net.URLEncoder;

@Named(value = "contactBean")
@RequestScoped
public class ContactBean implements Serializable {

    private String name;
    private String email;
    private String message;


    @Inject
    private LoginBean loginBean;

    @PostConstruct
    public void init() {
        if (loginBean != null && loginBean.getLoggedUser() != null) {
            this.name = loginBean.getLoggedUser().getFullName();
            this.email = loginBean.getLoggedUser().getEmail();
        }
    }









    // ===== GETTERS & SETTERS =====
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getMessage() {
        return message;
    }
 
    public void setMessage(String message) {
        this.message = message;
    }

    // ===== ACTION METHOD =====
public String send() {
    try {
        sendToSheet();
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO,
            "Thank you!", "Your message has been sent."));
        
        name = "";
        email = "";
        message = "";

    } catch (Exception e) {
        e.printStackTrace();
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_ERROR,
            "Error", "Unable to submit message."));
    }

    return null;
}

    // ===== EMAIL SENDING LOGIC =====
    private void sendToSheet() throws Exception {
    String url = "https://script.google.com/macros/s/AKfycbz2KBz9paoMgv5UgiFsja3YtAK4RsEq6B0ocQF9UmNJEKsEU7IdT6RdFWax2duX1y4VLg/exec";

    String data = "name=" + URLEncoder.encode(name, "UTF-8")
                + "&email=" + URLEncoder.encode(email, "UTF-8")
                + "&message=" + URLEncoder.encode(message, "UTF-8");

    java.net.URL obj = new java.net.URL(url);
    java.net.HttpURLConnection con = (java.net.HttpURLConnection) obj.openConnection();

    con.setRequestMethod("POST");
    con.setDoOutput(true);

    java.io.OutputStream os = con.getOutputStream();
    os.write(data.getBytes());
    os.flush();
    os.close();

    con.getResponseCode(); // Just to trigger request
}

}
