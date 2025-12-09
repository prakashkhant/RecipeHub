package cdi;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.faces.context.FacesContext;
import jakarta.faces.application.FacesMessage;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.Serializable;
import java.util.Properties;

@Named(value = "contactBean")
@RequestScoped
public class ContactBean implements Serializable {

    private String name;
    private String email;
    private String message;

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
            sendEmail();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Thank you!",
                            "Your message has been sent."));

            // clear form
            name = "";
            email = "";
            message = "";

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error",
                            "Something went wrong while sending email."));
        }

        return null; // stay on same page
    }

    // ===== EMAIL SENDING LOGIC =====
    private void sendEmail() throws MessagingException {
        // receiver
        String to = "prakashkhant1923@gmail.com";

        // sender (your Gmail) – change this
        String from = "prakashkhant1923@gmail.com";      // your Gmail ID
        String username = "prakashkhant1923@gmail.com";  // same as above
        String password = "iddr aqnz pzkt guei";    // Gmail App Password

        // Gmail SMTP settings
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        msg.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(to));
        msg.setSubject("New Contact Message - Healthy Recipes");

        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append("\n");
        sb.append("Email: ").append(email).append("\n\n");
        sb.append("Message:\n").append(message);

        msg.setText(sb.toString());

        Transport.send(msg);
    }
}
