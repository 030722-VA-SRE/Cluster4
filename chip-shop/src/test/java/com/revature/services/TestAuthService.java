package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.tomcat.util.http.parser.Authorization;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.dtos.UserDTO;
import com.revature.exceptions.AuthenticationException;
import com.revature.exceptions.AuthorizationException;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.repositories.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@ExtendWith(MockitoExtension.class)
class TestAuthService {
	
	private static Logger LOG = LoggerFactory.getLogger(TestAuthService.class);
			
	@Mock
	private UserRepository ur;
	
	private static JWTUtil jwt;
	
	@InjectMocks
	private AuthService as;
	
	@BeforeAll
	public static void setup() {
		jwt = new JWTUtil();
	}
	
	@Test
	void loginTest(){
		
		User user = new User(1, "Miguel", "Garcia", Role.USER);
		
		Mockito.when(ur.findUserByUsername("Miguel")).thenReturn(user);
		assertEquals(as.login("Miguel", "Garcia"), new UserDTO(user));
		
	}//end
	
	@Test
	void loginFailTest(){
		
		assertThrows(AuthenticationException.class, () -> as.login("Miguel", "Garcia"));
		
	}//end
	
	@Test
	void generateTokenTest() {
		
		UserDTO user = new UserDTO(new User(1, "Miguel", "Strong", Role.ADMIN));
				
		String token = jwt.generateToken(user);
		
		assertEquals(jwt.extractUsername(token), user.getUsername());
		
	}///end
		

	
}//end
