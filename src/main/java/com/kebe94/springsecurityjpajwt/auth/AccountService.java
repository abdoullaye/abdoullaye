package com.kebe94.springsecurityjpajwt.auth;

import com.kebe94.springsecurityjpajwt.entity.AppRole;
import com.kebe94.springsecurityjpajwt.entity.AppUser;

import java.util.List;

public interface AccountService {
    AppUser addUser(AppUser appUser);
    AppRole addRole(AppRole appRole);
    void addRoleTouser(String username, String roleName);
    AppUser loadUserByUsername(String username);
    List<AppUser> listUsers();

    AppUser findUserByEmail(String email);
}
