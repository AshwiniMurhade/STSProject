package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.entity.ErrorMessage;

@ControllerAdvice
@ResponseStatus  
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(DepartmentNotFoundException.class)
     public ResponseEntity<ErrorMessage> fun(DepartmentNotFoundException exception,WebRequest request){
    	 ErrorMessage em=new ErrorMessage(HttpStatus.NOT_FOUND,exception.getMessage());
    	 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(em);
     }
	@ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorMessage> fun1(EmployeeNotFoundException exception,WebRequest request){
   	 ErrorMessage em=new ErrorMessage(HttpStatus.NOT_FOUND,exception.getMessage());
   	 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(em);
    }
	
	
}
