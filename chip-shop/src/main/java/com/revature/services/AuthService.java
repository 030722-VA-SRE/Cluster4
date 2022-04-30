package com.revature.services;

import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dtos.UserDTO;
import com.revature.exceptions.AuthenticationException;
import com.revature.exceptions.AuthorizationException;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.repositories.UserRepository;

import io.jsonwebtoken.Claims;

@Service
public class AuthService {
	
	private UserRepository ur;
	private JWTUtil jwt;
	private static Logger LOG = LoggerFactory.getLogger(AuthService.class);
	
	@Autowired
	public AuthService(UserRepository ur, JWTUtil jwt) {
		super();
		this.ur = ur;
		this.jwt = jwt;
	}
	
	public UserDTO login(String username, String password) {
		
		LOG.debug("Attempting to log user in");
		// retrieve user from db by username, returns null if does not exist
		User principal = ur.findUserByUsername(username);
		
		// check that the uname and pass sent in req match the ones retrieved from db
		if(principal == null || !password.equals(principal.getPassword())) {
			throw new AuthenticationException("Attempted to login with username: " + username);
		}
		LOG.info("User succesfully logged in: id" + principal.getId()+ " name: "+ principal.getUsername());
		LOG.debug("User successfully logged in");
		return new UserDTO(principal);
	}//
	
	public UserDTO register(User user){
		
		LOG.debug("Attempting to register user");
		
		if(ur.findUserByUsername(user.getUsername()) != null) {
			throw new AuthenticationException("Username is not available");
		}
		else if(user.getUsername() == null) {
			throw new AuthenticationException("Username cannot be empty");
		}
		else if(user.getPassword() == null) {
			throw new AuthenticationException("Password cannot be empty");
		}
		
		user.setRole(Role.USER);
		
		User u = ur.save(user);
		LOG.info("New user was registered");
		LOG.debug("Done registering user");
		return new UserDTO(u);
		
	}//end
	
	public String generateToken(UserDTO principal) {
		
		LOG.debug("Generating token");
		
		String token = jwt.generateToken(principal);
		
		LOG.debug("Done generating token");
		
		return token;
	}
	/*
	public Claims verify(String token) {
		
		if(token == null) {
			LOG.warn("Not authorized");
			throw new AuthenticationException("Not authorized");
		}
		
		return jwt.extractAllClaims(token);
		
	}//end
	*/
	public boolean verify(String token, Integer id) {
		
		LOG.debug("Verifying token");
		
		if(token == null) {
			LOG.warn("Not authorized");
			throw new AuthenticationException("Not authorized");
		}//end
		
		Claims claim = jwt.extractAllClaims(token);
		/*
		 * id = -1 only admins have authorization
		 * id = 0 valid users have access
		 * id = some value only that userId and admin
		 */
		if(id.equals(-1) && claim.get("role").equals("ADMIN")){
			LOG.info("token verified successfully");
			LOG.debug("Done verifying token");
			MDC.put("userId", claim.get("id"));
			return true;
		}//end
		
		if(id.equals(0) && claim.get("role").equals("USER") || claim.get("role").equals("ADMIN")) {
			LOG.info("token verified successfully");
			LOG.debug("Done verifying token");
			MDC.put("userId", claim.get("id"));
			return true;
		}
		
		if(claim.get("id").equals(id) || claim.get("role").equals("ADMIN")){
			LOG.info("token verified successfully");
			LOG.debug("Done verifying token");
			MDC.put("userId", claim.get("id"));
			return true;
		}
		else {
			LOG.warn(claim.getSubject() + " is not authorized for this request");
			throw new AuthenticationException(claim.getSubject() + " not authorized");
		}
				
		
	}//end
	
		

}
