package com.kebe94.springsecurityjpajwt;

import com.kebe94.springsecurityjpajwt.auth.AccountService;
import com.kebe94.springsecurityjpajwt.entity.AppRole;
import com.kebe94.springsecurityjpajwt.entity.AppUser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SpringSecurityJpaJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJpaJwtApplication.class, args);
    }
    @Bean
    public BCryptPasswordEncoder getBCPE() {
        return new BCryptPasswordEncoder();
    }
//    @Bean
//    CommandLineRunner start(AccountService accountService){
//        return args -> {
//            accountService.addRole(new AppRole(null,"USER"));
//            accountService.addRole(new AppRole(null,"ADMIN"));
//            accountService.addRole(new AppRole(null,"MANAGER"));
//            accountService.addUser(new AppUser(null,"fatoumata","121081", new ArrayList<>()));
//            accountService.addUser(new AppUser(null,"halimetou","121081", new ArrayList<>()));
//
//            accountService.addRoleTouser("fatoumata1","USER");
//            accountService.addRoleTouser("halimetou3","MANAGER");
//
//        };
//    }

}
