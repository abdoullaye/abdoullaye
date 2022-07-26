//package com.kebe94.springsecurityjpajwt.auth;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
//import com.kebe94.springsecurityjpajwt.entity.AppUser;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//
//
//
//
//
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//@Autowired
//private AccountService userService;
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		AppUser user = userService.loadUserByUsername(username);
//		if(user == null) throw new UsernameNotFoundException(username);
//			Collection<GrantedAuthority>  authorities = new ArrayList<>();
//			user.getAppRoles().forEach(r->{
//				authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
//			});
//			try {
//				if (user.isEnabled() != true) {
//					throw new UsernameNotFoundException("Please enable your account.");
//				}else {
//					return new User(user.getUsername(),user.getPassword(), authorities);
//				}
//			} catch (UsernameNotFoundException e) {
//				e.printStackTrace();
//			}
//			return null;
//
//	}
//
//}
