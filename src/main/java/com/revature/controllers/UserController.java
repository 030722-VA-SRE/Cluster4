package com.revature.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dtos.UserDTO;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.UserService;

import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private UserService us;
	private AuthService as;
	private static Logger LOG =LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	public UserController(UserService us, AuthService as) {
		super();
		this.us = us;
		this.as = as;
	}//end
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> getAllUsers(@RequestHeader(value = "Authorization", required = false) String token){

		as.verify(token, -1);
		
		LOG.info("Users retrieved");
		return new ResponseEntity<>(us.getAllUsers(), HttpStatus.OK);
	}//end 
	
	@PostMapping
	public ResponseEntity<String> createUser(@RequestBody User user, @RequestHeader(value = "Authorization", required = false) String token) {
		
		as.verify(token, -1);
		
		User u = us.createUser(user);
		LOG.info("New user created");
		return new ResponseEntity<>("User " + u.getUsername() + " has been created.", HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Integer id, @RequestHeader(value = "Authorization", required = false) String token){

		as.verify(token, id);
			
		return new ResponseEntity<>(us.getUserById(id), HttpStatus.OK);
			
	}//end 
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("id") Integer id, @RequestHeader(value = "Authorization", required = false) String token){
				
		
			as.verify(token, -1);
			
			User u = us.updateUser(id, user);
			LOG.info("User was updated: {}", user.getUsername());
			return new ResponseEntity<>(u, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") Integer id, @RequestHeader(value = "Authorization", required = false) String token){
		
		as.verify(token, -1);
			
		us.deleteUser(id);
		LOG.info("Deleted user at id: " + id.intValue());
		return new ResponseEntity<>("User was deleted", HttpStatus.OK);
			
	}
	
}//end
