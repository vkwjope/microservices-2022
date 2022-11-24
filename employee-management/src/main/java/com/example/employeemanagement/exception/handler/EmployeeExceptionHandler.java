package com.example.employeemanagement.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.employeemanagement.exceptions.EmployeeAlreadyExistsException;
import com.example.employeemanagement.exceptions.EmployeeNotFoundException;

@ControllerAdvice
public class EmployeeExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = EmployeeNotFoundException.class)
	@ResponseStatus
	public ResponseEntity<Object> exception(EmployeeNotFoundException exception) {
		return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = EmployeeAlreadyExistsException.class)
	@ResponseStatus
	public ResponseEntity<Object> exception(EmployeeAlreadyExistsException exception) {
		return new ResponseEntity<>("Employee already exists", HttpStatus.NOT_FOUND);
	}

}
