package com.ashraf.library.security.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ashraf.library.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {

	
	@Value("${app.jwtSecret}")
	private String SECRET_KEY;

	public String extractId(String token) {
		
		return extractClaim(token, Claims::getSubject);
	}
	
	public Date extractExpiration(String token) {
		return (Date) extractClaim(token, Claims::getExpiration);
	}

	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}
	
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	public String generateToken(User user) {
		Logger log = Logger.getLogger(JwtUtil.class);
		log.info("inside jwtutil");
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, user.getId());
	}
	public String jwt() {
		
		return "akhbfakl,faefkaùf;aelf,aek,";
	}

	private String createToken(Map<String, Object> claims, int i) {
		
		return Jwts.builder().setClaims(claims).setSubject(String.valueOf(i)).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 *60)) // one minute
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}
	
	public Boolean validateToken(String token, User user) {
		final String id = extractId(token);
		return (id.equals(String.valueOf(user.getId())) && !isTokenExpired(token));
	}
	
	
	
}
