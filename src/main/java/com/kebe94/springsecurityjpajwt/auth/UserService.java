package com.kebe94.springsecurityjpajwt.auth;


import com.kebe94.springsecurityjpajwt.entity.AppRole;
import com.kebe94.springsecurityjpajwt.entity.AppUser;
import com.kebe94.springsecurityjpajwt.form.RegisterForm;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;

public interface UserService {

	public AppUser saveUser(AppUser user);
	public ResponseEntity<AppUser> saveUserAndRole(String email, String password, String role) throws ResourceNotFoundException;
	public AppRole saveRole(AppRole role);
	public void addUserToRole(String username, String roleName);
	public AppUser findUserByEmail(String username);
	 void updatePassword(String password, Long userId);

	public AppUser updateRole(AppUser appUserRequest, Long userId) throws ResourceNotFoundException;
	    AppUser save(RegisterForm registration);

}
