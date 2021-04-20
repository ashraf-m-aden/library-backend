package com.ashraf.library.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
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
import com.ashraf.library.services.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@CrossOrigin
@RestController
public class AuthenticationRestController {


	private UserService userService;

	
	private JwtUtil jwtTokenUtil;
	
	@Value("${app.jwtSecret}")
	private String SECRET_KEY;
	
	

	@Autowired
	public AuthenticationRestController(UserService userService, JwtUtil jwtTokenUtil) {
		this.userService = userService;
		this.jwtTokenUtil = jwtTokenUtil;
	}




	// we created an authenticate endpoint and the body of the request is the pseudo
	// and password yes
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authRequest)
			throws Exception {
		Logger log = Logger.getLogger(AuthenticationRestController.class);
		try {
			

			// then authentication manager try to authenticate with the pseudo and password
			new UsernamePasswordAuthenticationToken(authRequest.getusername(), authRequest.getPassword());
		} catch (BadCredentialsException e) {
			log.info("bad credentials");
			// if it doesnt work throw exception
			throw new Exception("Incorrect pseudo or password", e);
		}
		final User user = userService.findByUsernameAndPassword(authRequest.getusername(),
				authRequest.getPassword());

		// create jwt
		final String jwt = jwtTokenUtil.generateToken(user);
				// return the jwt
		return ResponseEntity.status(201).body(new AuthenticationResponse(jwt));
	}
	
	
	

	private String createToken(int i) {
		Map<String, Object> claims = new HashMap<>();

		return Jwts.builder().setClaims(claims).setSubject(String.valueOf(i)).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 *60)) // one minute
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}
	
	
	// we get the user info
	@GetMapping("/user")
	public ResponseEntity<User> getUserDetails(HttpServletRequest request) throws Exception {
		final String authorizationHeader = request.getHeader("Authorization");

		String jwt = authorizationHeader.substring(7);
		String id = jwtTokenUtil.extractId(jwt);
		// if it works loads the admin
		final User admin = userService.findUser(Integer.valueOf(id));

		// return the jwt
		return ResponseEntity.status(200).body(admin);
	}
}
