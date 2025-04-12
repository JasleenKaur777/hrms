package com.example.HRMS.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.HRMS.DTO.ResponseMessage;
@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getFieldErrors().forEach(error -> 
	        errors.put(error.getField(), error.getDefaultMessage())
	    );
	    return ResponseEntity.badRequest().body(errors);
	}
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ResponseMessage> getResourceNotFoundExceptionHandler(ResourceNotFoundException ex){
		String message= ex.getMessage();
		ResponseMessage res=new ResponseMessage(message,false);
		return new ResponseEntity<ResponseMessage>(res,HttpStatus.NOT_FOUND);
	}
}
