package com.kebe94.springsecurityjpajwt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    @Column(unique = true)
    private String email;
    private String password;
    @Column(unique = true)
    private String username;
    private boolean enabled;
    private Date last_login;
    private Date password_requested_at;
    private Date date_create;
    //	@Column(nullable = false, unique = true)
    private String confirmationToken;
    private Date confirmationDate;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<AppRole> appRoles = new ArrayList<>();

    @OneToMany(mappedBy = "appUser", fetch = FetchType.LAZY)
    private Collection<Client> clients;

    public  AppUser(Long id, String username, String password ) {
        this.userId = id;
        this.username = username;
        this.password = password;
    }

    public <E> AppUser(Long id, String username, String password, ArrayList<E> es) {
        this.userId = id;
        this.username = username;
        this.password = password;
    }
    public void setExpiryDate(int minutes){
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, minutes);
        this.confirmationDate = now.getTime();
    }

    public boolean isExpired() {
        return new Date().after(this.confirmationDate);
    }
}
