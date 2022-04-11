package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.dtos.UserDTO;
import com.revature.exceptions.UserAlreadyExistException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
class TestUserService {

	@Mock
	private UserRepository ur;
	
	@InjectMocks
	UserService us;
	
	@Test
	void createUserTest(){
		
		User user = new User(1, "Miguel", "Strong", Role.ADMIN);
		Mockito.when(ur.save(user)).thenReturn(user);
		
		assertEquals(us.createUser(user), user);
		
	}//end 
	
	@Test
	void createUserFailTest() {
		User user = new User(1, "Miguel", "Strong", Role.ADMIN);
		
		Mockito.when(ur.findUserByUsername("Miguel")).thenReturn(user);
		assertThrows(UserAlreadyExistException.class, () -> us.createUser(user));
		
	}//end
	
	@Test
	void updateUserTest() {
		User user = new User(1, "Miguel", "Strong", Role.ADMIN);
		
		Mockito.when(ur.findById(1)).thenReturn(Optional.of(user));
		Mockito.when(ur.save(user)).thenReturn(user);
		assertEquals(us.updateUser(1, user), user);
		
	}//end
	
	@Test
	void updateUserFailTest() {
		User user = new User(1, "Miguel", "Strong", Role.ADMIN);
		
		assertThrows(UserNotFoundException.class, () -> us.updateUser(1, user));
		
	}//end
	
	@Test
	void getAllUsersTest() {
		User user1 = new User(1, "Miguel", "Strong", Role.ADMIN);
		User user2 = new User(1, "Garcia", "Strong", Role.USER);
		User user3 = new User(1, "Mike", "Strong", Role.USER);
		
		List<User> userList = new ArrayList<>();
		
		userList.add(user1);
		userList.add(user2);
		userList.add(user3);
		
		List<UserDTO> usersDto = userList.stream()
				.map((user) -> new UserDTO(user))
				.collect(Collectors.toList());
		
		Mockito.when(ur.findAll()).thenReturn(userList);
		assertEquals(us.getAllUsers(), usersDto);
		
	}//end
	
	@Test
	void getUserByIdTest() {
		User user = new User(1, "Miguel", "Strong", Role.ADMIN);
		
		Mockito.when(ur.findById(1)).thenReturn(Optional.of(user));
		assertEquals(us.getUserById(1), new UserDTO(user));
		
	}//end
	
	@Test
	void getUserByIdFailTest(){
		
		assertThrows(UserNotFoundException.class, () -> us.getUserById(1));
		
	}//end
	
	@Test
	void deleteUserFailTest() {
		
		assertFalse(us.deleteUser(1));
		//assertThrows(UserNotFoundException.class, () -> us.deleteUser(1));
		
	}//end
	
	@Test
	void deleteUserTest() {
		
		User user = new User(1, "Miguel", "Strong", Role.ADMIN);
		Mockito.when(ur.findById(1)).thenReturn(Optional.of(user));
		assertTrue(us.deleteUser(1));
	}
	
}//end
