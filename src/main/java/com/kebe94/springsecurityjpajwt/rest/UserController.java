//package banque.com.sonidev.web;

//import java.io.IOException;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.UUID;
//
//import javax.mail.MessagingException;
//import javax.servlet.http.HttpServletRequest;
//
//
//import banque.com.sonidev.entity.Person;
//import banque.com.sonidev.exception.ResourceNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.security.core.Authentication;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.annotation.*;
//
//import banque.com.sonidev.dao.AppUserRepository;
//import banque.com.sonidev.dao.RoleRepository;
//import banque.com.sonidev.entity.AppUser;
//import banque.com.sonidev.entity.RegisterForm;
//import banque.com.sonidev.entity.Role;
//import banque.com.sonidev.service.UserService;
//@RestController
//public class UserController {
//	@Autowired private UserService userService;
//	@Autowired
//	RoleRepository roleRepository;
//
//	@Autowired
//	private AppUserRepository userRepository;
//
//	@GetMapping(value="/userAuth")
//	AppUser appUsers(Authentication auth) {
//		return userRepository.findByEmail(auth.getName());
//
//	}
//	@PostMapping(value="/addRole")
//   public ResponseEntity<Object> saveRole(@RequestBody Role role){
//	   return new ResponseEntity<>(roleRepository.save(role),HttpStatus.OK);
//   }
//
	
//	Map<String, String> errers;
//    @RequestMapping(value = "/registration", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseEntity<Object> registration(@RequestBody RegisterForm userForm, BindingResult result) {
//
//    	if(result.hasErrors()) {
//    		errers = new HashMap<>();
//    	for (FieldError  error : result.getFieldErrors()) {
//    		errers.put(error.getField(), error.getDefaultMessage());
//    	}
//    		return new ResponseEntity<>(errers, HttpStatus.NOT_ACCEPTABLE);
//    	}
//        AppUser existing = userService.findUserByEmail(userForm.getEmail());
//        if (existing!=null) {
//    		throw new RuntimeException("Votre addresse mail,  n'est pas disponible.");
//    	}
//
//        return  new ResponseEntity<>(userService.save(userForm), HttpStatus.OK);
//    }

//	@RequestMapping(value = "/registration/person", method = RequestMethod.POST)
//	public ResponseEntity<Object> registrationAnRole(@RequestParam String email, @RequestParam String password, @RequestParam String role, @RequestParam Person person) throws ResourceNotFoundException {

//		if(result.hasErrors()) {
//			errers = new HashMap<>();
//			for (FieldError  error : result.getFieldErrors()) {
//				errers.put(error.getField(), error.getDefaultMessage());
//			}
//			return new ResponseEntity<>(errers, HttpStatus.NOT_ACCEPTABLE);
//		}
//		AppUser existing = userService.findUserByEmail(email);
//		if (existing!=null) {
//			throw new RuntimeException("Votre addresse mail,  n'est pas disponible.");
//		}
//		return  new ResponseEntity<>(userService.saveUserAndRole(email, password, role, person), HttpStatus.OK);
//	}
//
//
//	  @RequestMapping(value = "/confirmation/{token}", method = RequestMethod.GET)
//	  public ResponseEntity<Object> passwordResetToken(@PathVariable String token){
//	        AppUser resetToken = userRepository.findByConfirmationToken(token);
//	        if(resetToken == null) {
//	          	throw new RuntimeException("Impossible de trouver le jeton de réinitialisation du mot de passe.");
//
//	       }else if(resetToken.isExpired()) {
//	    	   	throw new RuntimeException("Le jeton a expiré, veuillez demander une nouvelle réinitialisation du mot de passe.");
//	        }
//	        resetToken.setEnabled(true);
//	        userRepository.save(resetToken);
//	        return new ResponseEntity<Object>(userRepository.findByConfirmationToken(token),HttpStatus.OK);
//
//	  }
//	@PatchMapping(value="/updateRole/{userId}")
//	public void updateRole(@RequestBody AppUser appUser, @PathVariable Long userId) throws ResourceNotFoundException {
//		 userService.updateRole(appUser, userId);
//	}
//}
