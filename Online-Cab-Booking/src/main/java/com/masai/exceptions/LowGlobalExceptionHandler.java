package com.masai.exceptions;

import java.time.LocalDateTime;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class LowGlobalExceptionHandler {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyError> exceptionHandler(Exception e,WebRequest wr){
		MyError err = new MyError(LocalDateTime.now(),e.getMessage(),wr.getDescription(false));
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
}
