package health.model.service;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;

public class ContactUsServiceModel {
    private String username;
    private String email;
    private String message;

    public ContactUsServiceModel() {
    }

    @Length(min = 3, message = "Length must be at least 3 chars.")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Length(min = 10, message = "Message must be at least 10 chars.")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
