package com.revature.exceptions;

import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


//@Controller advice allows you to handle exceptions across the whole application
@ControllerAdvice
public class CustomizedExceptionHandling{
	
	private static final Logger LOG = LoggerFactory.getLogger(CustomizedExceptionHandling.class);
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception) {
		LOG.warn("User not found exception was handled.", exception);
		return new ResponseEntity<>(new ExceptionResponse("User not found", LocalDateTime.now()), HttpStatus.NOT_FOUND);
	}//end 
	
	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<Object> handleUserAlreadyExistException(UserAlreadyExistException exception){
		LOG.warn("User already exist exception was handled.", exception);
		return new ResponseEntity<>(new ExceptionResponse("User already exist", LocalDateTime.now()), HttpStatus.UNPROCESSABLE_ENTITY);
	}//end
	
	@ExceptionHandler(BrandAlreadyExistException.class)
	public ResponseEntity<Object> handleBrandAlreadyExistException(BrandAlreadyExistException exception){
		LOG.warn("Brand already exist exception was handled.", exception);
		return new ResponseEntity<>(new ExceptionResponse("Brand already exist", LocalDateTime.now()), HttpStatus.UNPROCESSABLE_ENTITY);
	}//end
	
	@ExceptionHandler(BrandNotFoundException.class)
	public ResponseEntity<Object> handleBrandNotFoundException(BrandNotFoundException exception) {
		LOG.warn("Brand not found exception was handled.", exception);
		return new ResponseEntity<>(new ExceptionResponse("Brand not found", LocalDateTime.now()), HttpStatus.NOT_FOUND);
	}//end
	
	@ExceptionHandler(FlavorAlreadyExistException.class)
	public ResponseEntity<Object> handleFlavorAlreadyExistException(FlavorAlreadyExistException exception){
		LOG.warn("Flavor already exist exception was handled.", exception);
		return new ResponseEntity<>(new ExceptionResponse("Flavor already exist", LocalDateTime.now()), HttpStatus.UNPROCESSABLE_ENTITY);
	}//end
	
	@ExceptionHandler(FlavorNotFoundException.class)
	public ResponseEntity<Object> handleFlavorNotFoundException(FlavorNotFoundException exception) {
		LOG.warn("Flavor not found exception was handled.", exception);
		return new ResponseEntity<>(new ExceptionResponse("Flavor not found", LocalDateTime.now()), HttpStatus.NOT_FOUND);
	}//end
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
		LOG.error("Data integrity violation exception.", exception);
		return new ResponseEntity<>(new ExceptionResponse("Data Integrity Violation", LocalDateTime.now()), HttpStatus.CONFLICT);
	}//end
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
		LOG.warn("Method argument type mismatch exception was handled.", exception);
		return new ResponseEntity<>(new ExceptionResponse("Method argument type mismatch", LocalDateTime.now()), HttpStatus.BAD_REQUEST);
	}//end
	
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<Object> handleAuthenticationException(AuthenticationException e) {
		LOG.warn("Authentication exception was handled.", e);
		return new ResponseEntity<>(new ExceptionResponse("Wrong creditials", LocalDateTime.now()), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<Object> handleAuthenticationException(AuthorizationException e) {
		LOG.warn("Authorization exception was handled.", e);
		return new ResponseEntity<>(new ExceptionResponse("Not Aurthorized", LocalDateTime.now()), HttpStatus.FORBIDDEN);
	}
}//end
