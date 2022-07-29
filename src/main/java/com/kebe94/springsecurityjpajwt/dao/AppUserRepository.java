package com.kebe94.springsecurityjpajwt.dao;

import com.kebe94.springsecurityjpajwt.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
    AppUser findByEmail(String email);
}
