package com.ashraf.library.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ashraf.library.entity.User;
import com.ashraf.library.services.UserServiceImpl;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{

	@Autowired
	private UserServiceImpl adminServImpl;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authorizationHeader = request.getHeader("Authorization");
		String id = null;
		String jwt = null;
		
		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			
			jwt = authorizationHeader.substring(7);
			id = jwtUtil.extractId(jwt);
		}
		// check if the id is not null and nobody else is already authenticated
		if(id != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			User userDetail = this.adminServImpl.findUser(Integer.valueOf(id));
			// if the jwt is valid for this givern user
			if(jwtUtil.validateToken(jwt, userDetail)) {
				// authenticate the user
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetail, null, userDetail.getAuthorities());
				usernamePasswordAuthenticationToken
				.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		// hand over the controller to the next chain of work
		filterChain.doFilter(request, response);
		
	}

	
}
