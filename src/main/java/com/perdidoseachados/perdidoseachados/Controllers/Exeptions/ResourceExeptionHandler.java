package com.perdidoseachados.perdidoseachados.Controllers.Exeptions;


import com.perdidoseachados.perdidoseachados.Servicies.exeptions.DataBaseExeption;
import com.perdidoseachados.perdidoseachados.Servicies.exeptions.DuplicateStockException;
import com.perdidoseachados.perdidoseachados.Servicies.exeptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ResourceExeptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandartError> entityNotFound(ResourceNotFoundException e,HttpServletRequest
			request){
		
		StandartError err = new StandartError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setError("Resource not found");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		
	}
	
	@ExceptionHandler(DataBaseExeption.class)
	public ResponseEntity<StandartError> database(DataBaseExeption e, HttpServletRequest
			request){
		
		StandartError err = new StandartError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setError("DataBase Execption");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
		
	}
	
	@ExceptionHandler(DuplicateStockException.class)
	public ResponseEntity<StandartError> database(DuplicateStockException e, HttpServletRequest
			request){
		
		StandartError err = new StandartError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.CONFLICT.value());
		err.setError("Duplicate Stock Exception");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
		
	}
	/*
	@ExceptionHandler(ValidationExcepti.class)
	public ResponseEntity<ValidationError> database(ValidationExceptio e, HttpServletRequest
			request){
		
		ValidationError err = new ValidationError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
		err.setError("Validation Execption");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		

		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
		
	}*/
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> database(MethodArgumentNotValidException e, HttpServletRequest
			request){
		
		ValidationError err = new ValidationError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
		err.setError("Validation Execption");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
	     for (FieldError Error : e.getBindingResult().getFieldErrors()){
			err.addError(Error.getField(), Error.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
		
	}

}
