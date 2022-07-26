package com.kebe94.springsecurityjpajwt.dao;

import com.kebe94.springsecurityjpajwt.entity.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    AppRole findByRoleName(String roleName);
}
