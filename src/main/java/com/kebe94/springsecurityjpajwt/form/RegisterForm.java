package com.kebe94.springsecurityjpajwt.form;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.kebe94.springsecurityjpajwt.entity.AppRole;
import org.springframework.lang.NonNull;

import lombok.Data;

@Data
public class RegisterForm {


    @NotBlank(message = "s'il vous plaît entrez votre password")
    @Size(min = 6, message = "la taille doit être comprise entre 6 et 20", max = 20)
    private String password;
    @NotBlank(message = "s'il vous plaît entrez votre password")
    @Size(min = 6, message = "la taille doit être comprise entre 6 et 20", max = 20)
    private String repassword;
    private boolean actived;
    @NotBlank(message = "s'il vous plaît entrez votre email")
    @Email(message = "L'email devrait être valide")
    private String email;
    @NotBlank(message = "s'il vous plaît entrez votre username")
    @Email(message = "L'username devrait être valide")
    private String username;
    private AppRole role;
    private Date last_login;
    private Date password_requested_at;
    @Column(nullable = false, unique = true)
    private String confirmationToken;
    private Date confirmationDate;

    public void setExpiryDate(int minutes) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, minutes);
        this.confirmationDate = now.getTime();
    }

    public boolean isExpired() {
        return new Date().after(this.confirmationDate);
    }

}
