package com.kebe94.springsecurityjpajwt.config;

import java.io.IOException;
import java.util.*;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kebe94.springsecurityjpajwt.entity.AppUser;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JWTAutorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers",
				"Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, authorization"
		);
		response.addHeader("Access-Control-Expose-Headers",
				"Access-Control-Allow-Origin,Access-Control-Allow-Credentials, authorization");
		response.addHeader("Access-Control-Allow-Methods", "OPTIONS,GET,POST,HEAD,PUT,DELETE,PATCH");
		if(request.getMethod().equals("OPTIONS")) {
			response.setStatus(HttpServletResponse.SC_OK);
		}else {
			String jwtString = request.getHeader(SecurityConstants.HEADER_STRING);
			if(jwtString==null || !jwtString.startsWith(SecurityConstants.TOKEN_PREFIX)) {
				filterChain.doFilter(request, response);
				return;
			}
			String token = jwtString.replace(SecurityConstants.TOKEN_PREFIX, "");

			try {
				Claims claims = Jwts.parser()
						.setSigningKey(SecurityConstants.SECRET)
						.parseClaimsJws(jwtString.replace(SecurityConstants.TOKEN_PREFIX,""))
						.getBody();
				String username= claims.getSubject();
				ArrayList<Map<String, String>> roles=(ArrayList<Map<String, String>>)
						claims.get("roles");
				Collection<GrantedAuthority> autorizations= new ArrayList<>();
				roles.forEach(r->{
					autorizations.add(new SimpleGrantedAuthority(r.get("authority")));
				});
				UsernamePasswordAuthenticationToken authenticationToken=
						new UsernamePasswordAuthenticationToken(username, null, autorizations);
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			} catch (Exception e) {
				// In case of failure. Make sure it's clear; so guarantee user won't be authenticated
				SecurityContextHolder.clearContext();
			}
			filterChain.doFilter(request, response);
		}
	}

}

