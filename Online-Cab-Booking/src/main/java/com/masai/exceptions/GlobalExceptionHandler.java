package com.masai.exceptions;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserNameAlreadyExist.class)
	public ResponseEntity<MyError> userNameAlreadyExist(UserNameAlreadyExist unae,WebRequest wr){
		MyError err = new MyError(LocalDateTime.now(),unae.getMessage(),wr.getDescription(false));
		ResponseEntity<MyError> re = new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
		return re;
	}
	
	@ExceptionHandler(UserDoesNotExist.class)
	public ResponseEntity<MyError> heSheDoesNotExist(UserDoesNotExist unae,WebRequest wr){
		MyError err = new MyError(LocalDateTime.now(),unae.getMessage(),wr.getDescription(false));
		ResponseEntity<MyError> re = new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
		return re;
	}
	
	
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<MyError> contraintViolated(ConstraintViolationException cve,WebRequest wr){
		MyError err = new MyError(LocalDateTime.now(),cve.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList()).get(0).toString(), wr.getDescription(false));
		ResponseEntity<MyError> re = new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
		return re;
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyError> methodArgsInvalid(MethodArgumentNotValidException mve,WebRequest wr) {
		MyError err = new MyError(LocalDateTime.now(), mve.getBindingResult().getFieldError().getDefaultMessage()
, wr.getDescription(false));
		ResponseEntity<MyError> re = new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
		return re;
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyError> noHandlerFound(NoHandlerFoundException nhfe,WebRequest wr){
		MyError err = new MyError(LocalDateTime.now(), nhfe.getMessage(), wr.getDescription(false));
		ResponseEntity<MyError> re = new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
		return re;
	}
	
	@ExceptionHandler(TripInProgress.class)
	public ResponseEntity<MyError> tripInProgress(TripInProgress mve,WebRequest wr) {
		MyError err = new MyError(LocalDateTime.now(), mve.getMessage()
, wr.getDescription(false));
		ResponseEntity<MyError> re = new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
		return re;
	}

	@ExceptionHandler(CabDriverNotAvailableException.class)
	public ResponseEntity<MyError> tripInProgress(CabDriverNotAvailableException mve,WebRequest wr) {
		MyError err = new MyError(LocalDateTime.now(), mve.getMessage()
            , wr.getDescription(false));
		ResponseEntity<MyError> re = new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
		return re;
	}

}
