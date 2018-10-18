package br.com.udemycurso.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.udemycurso.services.exceptions.AuthorizationException;
import br.com.udemycurso.services.exceptions.BadRequestException;
import br.com.udemycurso.services.exceptions.NotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<StandardError> notFound(NotFoundException e , HttpServletRequest request){
		
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value() ,e.getMessage(),System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<StandardError> badRequest(BadRequestException e , HttpServletRequest request){
		
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value() ,e.getMessage(),System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e , HttpServletRequest request){
		
		ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value() ,"Erro de validação",System.currentTimeMillis());
		for(FieldError x : e.getBindingResult().getFieldErrors()) {			
			err.addError(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> accessDenied(AuthorizationException e , HttpServletRequest request){
		
		StandardError err = new StandardError(HttpStatus.FORBIDDEN.value() ,e.getMessage(),System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}
	
}
