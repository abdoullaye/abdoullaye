package com.kebe94.springsecurityjpajwt.config;


import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kebe94.springsecurityjpajwt.config.SecurityConstants;
import com.kebe94.springsecurityjpajwt.entity.AppUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;






public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		super();

		this.authenticationManager = authenticationManager;
	}
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)

			throws AuthenticationException {
		AppUser appUser = null;
		String username = request.getParameter("email");
		String password = request.getParameter("password");
		try {
			appUser =  new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("email"+appUser.getEmail());
		System.out.println("password"+appUser.getPassword());
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
				appUser.getUsername(), appUser.getPassword(), Collections.emptyList());
		return authenticationManager.authenticate(authToken);
	}
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
											FilterChain chain,
											Authentication authResult) throws IOException, ServletException {


		User strinUser =  (User) authResult.getPrincipal();
		String jwt= Jwts.builder()
				.setSubject(strinUser.getUsername())
				.setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS256, SecurityConstants.SECRET)
				.claim("roles", strinUser.getAuthorities())
				.compact();
		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX+jwt);

	}


}
