package com.kebe94.springsecurityjpajwt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long clientId;
    @Column(unique = true)
    private String email;
    private String lastName;
    private String firstName;
    private boolean actived;
    private Date createDate;
    @ManyToOne
    private AppUser appUser;

}
