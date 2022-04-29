package com.example.rest.webservices.restfulwebservices.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

// Make it as a @ControllerAdvice to make it applicable across all controllers that are
// annotated by @RestController (UserController, HelloWorldController)
@ControllerAdvice
// Make it as a @RestController since it is providing a response back
@RestController
// This is a class that extends the base class called 'ResponseEntityExceptionHandler', a base class
// that has all the basic error handling methods.
// We extend this class to customize our own exception handler.
public class CustomizedResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {
  
  // This is a method created by ourselves that handles the 'UserNotFound' exception
  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex,
                                                                  WebRequest request) {
    ExceptionResponse exceptionResponse =
            new ExceptionResponse(new Date(),
                                  ex.getMessage(),
                                  request.getDescription(false));
    return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
  }
  
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
          MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
          WebRequest request) {
    
    ExceptionResponse exceptionResponse =
            new ExceptionResponse(new Date(),
                                  "Validation Failed",
                                  ex.getBindingResult().toString());
    return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
  }
}
