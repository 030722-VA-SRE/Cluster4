package com.revature.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.revature.dtos.UserDTO;
import com.revature.models.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTUtil {
	
	private String SECRET_KEY = "TOPSECRET";
	
	private String createToken(Map<String, Object> claims, UserDTO user){
		
		//Sets the JWT payload to be a JSON Claims instance
		return Jwts.builder().setClaims(claims).setSubject(user.getUsername()).setIssuedAt(Date.from(Instant.now()))
				.setExpiration(Date.from(Instant.now().plus(20, ChronoUnit.MINUTES)))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
				
	}//end
	
	public String generateToken(UserDTO user){
		Map<String, Object> claims = new HashMap<>();
		claims.put("id", user.getId());
		claims.put("role", user.getRole().toString());
		
		return createToken(claims, user);
	}//end
	
	public Claims extractAllClaims(String token){
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	public String extractUsername(String token){
		return extractClaim(token, Claims::getSubject);
	}
	
	/*
	public Date extractExpiration(String token){
		return extractClaim(token, Claims::getExpiration);
	}
	
	private Boolean isTokenExpired(String token){
		return extractExpiration(token).before(Date.from(Instant.now()));
	}
	
	public Boolean validateToken(String token, User user){
		String username = extractUsername(token);
		return(username.equals(user.getUsername()) && !isTokenExpired(token));
	}
	*/
	
	
}//end
