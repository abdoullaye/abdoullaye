//package com.kebe94.springsecurityjpajwt.auth;
//
//import java.util.Arrays;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
//
//import com.kebe94.springsecurityjpajwt.dao.AppRoleRepository;
//import com.kebe94.springsecurityjpajwt.dao.AppUserRepository;
//import com.kebe94.springsecurityjpajwt.entity.AppUser;
//
//import com.kebe94.springsecurityjpajwt.exception.ResourceNotFoundException;
//import com.kebe94.springsecurityjpajwt.form.RegisterForm;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//
//
//
//
//
//@Service
//@Transactional
//public class UserServiceImpl implements UserService {
//
//
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//	@Autowired
//	private AppUserRepository appUserRepository;
//	@Autowired
//	private AppRoleRepository appRoleRepository;
//
//	public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder) {
//		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//	}
//
//	@Override
//	public AppUser saveUser(AppUser user) {
//		String hashPW = bCryptPasswordEncoder.encode(user.getPassword());
//		user.setPassword(hashPW);
//		return appUserRepository.save(user);
//	}
//
//	@Override
//	public ResponseEntity<AppUser> saveUserAndRole(String email, String password, String role, Person person)throws ResourceNotFoundException {
//
//		AppUser appUser = new AppUser();
//		appUser.setEmail(email);
//		appUser.setRoles(Arrays.asList(appRoleRepository.findByName(role)));
//		appUser.setPassword(bCryptPasswordEncoder.encode(password));
//		Date date = new Date();
//		appUser.setDate_create(date);
//		appUser.setConfirmationToken(UUID.randomUUID().toString());
//		appUser.setExpiryDate(730);
//		appUser.setPerson(person);
//		Mail mail = new Mail();
//		mail.setFrom("no-reply@sonidev.com");
//		mail.setTo(email);
//		mail.setSubject("sonidev application");
//		Map<String, Object> model = new HashMap<>();
//		model.put("token", appUser);
//		model.put("signature", "https://soninkara.fr");
//		String url = "http://localhost:4200";
//		model.put("resetUrl", url + "/confirmation/" + appUser.getConfirmationToken());
//		mail.setModel(model);
//		emailService.sendEmail(mail);
//		return new ResponseEntity<>(appUserRepository.save(appUser), HttpStatus.ACCEPTED);
//	}
//
//
//
//	@Override
//	public Role saveRole(Role role) {
//		return appRoleRepository.save(role);
//	}
//
//	@Override
//	public void addUserToRole(String username, String roleName) {
//		Role appRole = appRoleRepository.findByName(roleName);
//		AppUser appUser = appUserRepository.findByEmail(username);
//		appUser.getRoles().add(appRole);
//
//	}
//
//	@Override
//	public AppUser findUserByEmail(String username) {
//		return appUserRepository.findByEmail(username);
//	}
//
//	@Override
//	public void updatePassword(String password, Long userId) {
//		appUserRepository.updatePassword(password, userId);
//
//	}
//
//	@Override
//	public AppUser updateRole(AppUser appUserRequest, Long userId) throws ResourceNotFoundException {
//
//
//				return appUserRepository.findById(userId).map(user -> {
//			user.setLast_login(appUserRequest.getLast_login());
//			user.setUsername(appUserRequest.getUsername());
//			user.setPassword(appUserRequest.getPassword());
//			user.setEmail(appUserRequest.getEmail());
//			user.setTokens(appUserRequest.getTokens());
//
//			user.setRoles(Arrays.asList(appRoleRepository.findByName("ROLE_EMPLOYER")));
//			user.setConfirmationToken(appUserRequest.getConfirmationToken());
//			return appUserRepository.save(user);
//		}).orElseThrow(()->new ResourceNotFoundException("une erreur sur venue"));
//	}
//
////	@Override
////	public AppUser updateRole(AppUser appUserRequest, Long userId) {
////		return appUserRepository.findById(userId).map(appUser -> {
////			appUser.setLast_login(appUserRequest.getLast_login());
////			appUser.setUsername(appUserRequest.getUsername());
////			appUser.setRoles(appUserRequest.getRoles());
////			appUser.setPassword(appUserRequest.getPassword());
////			appUser.setEmail(appUserRequest.getEmail());
////			appUser.setToken(appUserRequest.getToken());
////		})
////	}
//
//	public AppUser save(RegisterForm userForm) {
//		AppUser user = new AppUser();
//		user.setRoles(Arrays.asList(appRoleRepository.findByName(userForm.getRole().getName())));
//	//	data(user, userForm);
//		return appUserRepository.save(user);
//
//	}
//
////	public void addUser(AppUser user, RegisterForm userForm) {
////		user.setEmail(userForm.getEmail());
////		user.setPassword(bCryptPasswordEncoder.encode(userForm.getPassword()));
////		Date date = new Date();
////		user.setDate_create(date);
////		user.setConfirmationToken(UUID.randomUUID().toString());
////		user.setExpiryDate(730);
////		user.setPerson(userForm.getPerson());
////		Mail mail = new Mail();
////		mail.setFrom("no-reply@sonidev.com");
////		mail.setTo(userForm.getEmail());
////		mail.setSubject("sonidev application");
////		Map<String, Object> model = new HashMap<>();
////		model.put("token", userForm);
////		model.put("signature", "https://soninkara.fr");
////		String url = "http://localhost:4200";
////		model.put("resetUrl", url + "/confirmation/" + user.getConfirmationToken());
////		mail.setModel(model);
////
////		emailService.sendEmail(mail);
////	}
//
//
////	public void data(AppUser user, RegisterForm userForm) {
////		user.setEmail(userForm.getEmail());
////		user.setPerson(userForm.getPerson());
////		user.setPassword(bCryptPasswordEncoder.encode(userForm.getPassword()));
////		Date date = new Date();
////		user.setDate_create(date);
////		user.setConfirmationToken(UUID.randomUUID().toString());
////		user.setExpiryDate(730);
////		Mail mail = new Mail();
////		mail.setFrom("no-reply@sonidev.com");
////		mail.setTo(userForm.getEmail());
////		mail.setSubject("sonidev application");
////		Map<String, Object> model = new HashMap<>();
////		model.put("token", userForm);
////		model.put("signature", "https://soninkara.fr");
////		String url = "http://localhost:4200";
////		model.put("resetUrl", url + "/confirmation/" + user.getConfirmationToken());
////		mail.setModel(model);
////
////		emailService.sendEmail(mail);
////	}
//
//
//
//}
