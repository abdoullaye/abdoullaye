package com.kebe94.springsecurityjpajwt.dao;

import com.kebe94.springsecurityjpajwt.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
    AppUser findByEmail(String email);
}
