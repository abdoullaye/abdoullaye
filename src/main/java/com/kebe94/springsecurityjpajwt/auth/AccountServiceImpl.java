package com.kebe94.springsecurityjpajwt.auth;

import com.kebe94.springsecurityjpajwt.dao.AppRoleRepository;
import com.kebe94.springsecurityjpajwt.dao.AppUserRepository;
import com.kebe94.springsecurityjpajwt.entity.AppRole;
import com.kebe94.springsecurityjpajwt.entity.AppUser;
import com.kebe94.springsecurityjpajwt.form.RegisterForm;

import java.util.Arrays;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService{
    private final AppUserRepository userRepository;
    private final AppRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AppUser addUser(AppUser user) {
        String pw = user.getPassword();
        user.setPassword(passwordEncoder.encode(pw));
        user.setAppRoles(Arrays.asList(roleRepository.findByRoleName("USER")));
        Date date = new Date();
        user.setDate_create(date);
        user.setConfirmationToken(UUID.randomUUID().toString());
        user.setExpiryDate(730);

        return userRepository.save(user);
    }

    @Override
    public AppRole addRole(AppRole appRole) {
        return roleRepository.save(appRole);
    }

    @Override
    public void addRoleTouser(String username, String roleName) {
        AppUser appUser = userRepository.findByUsername(username);
        AppRole appRole = roleRepository.findByRoleName(roleName);
        appUser.getAppRoles().add(appRole);

    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<AppUser> listUsers() {
        return userRepository.findAll();
    }

    @Override
    public AppUser findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
