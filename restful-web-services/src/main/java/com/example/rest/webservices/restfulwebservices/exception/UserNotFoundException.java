package com.example.rest.webservices.restfulwebservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
// This is an exception class that takes a String as an error message.
// It will be used at 'CustomizedResponseEntityResponseHandler'
public class UserNotFoundException
        extends RuntimeException {
  public UserNotFoundException(String message) {
    super(message);
  }
}
