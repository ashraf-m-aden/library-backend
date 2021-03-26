package com.ashraf.library.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashraf.library.entity.User;
import com.ashraf.library.security.authentication.AuthenticationRequest;
import com.ashraf.library.security.authentication.AuthenticationResponse;
import com.ashraf.library.security.jwt.JwtUtil;
import com.ashraf.library.services.UserServiceImpl;

@RestController
public class AuthenticationRestController {

	@Autowired
	private UserServiceImpl adminServImpl;

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private JwtUtil jwtTokenUtil;


	// we created an authenticate endpoint and the body of the request is the pseudo
	// and password yes
	@CrossOrigin(origins = "https://nationallibrary-13f4b.web.app")
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authRequest)
			throws Exception {

		try {
			// then authentication manager try to authenticate with the pseudo and password

			new UsernamePasswordAuthenticationToken(authRequest.getusername(), authRequest.getPassword());
		} catch (BadCredentialsException e) {
			// if it doesnt work throw exception
			throw new Exception("Incorrect pseudo or password", e);
		}
		// if it works loads the admin with the username because we configuref our
		// loadUserbyUsername
		final User admin = adminServImpl.findByUsernameAndPassword(authRequest.getusername(),
				authRequest.getPassword());
		// create jwt
		final String jwt = jwtTokenUtil.generateToken(admin);

		// return the jwt
		return ResponseEntity.status(201).body(new AuthenticationResponse(jwt));
	}

	// we get the user info
	@CrossOrigin(origins = "https://nationallibrary-13f4b.web.app")
	@GetMapping("/user")
	public ResponseEntity<User> getUserDetails(HttpServletRequest request) throws Exception {

		final String authorizationHeader = request.getHeader("Authorization");

		String jwt = authorizationHeader.substring(7);
		String id = jwtTokenUtil.extractId(jwt);
		// if it works loads the admin
		final User admin = adminServImpl.findUser(Integer.valueOf(id));

		// return the jwt
		return ResponseEntity.status(200).body(admin);
	}
}
