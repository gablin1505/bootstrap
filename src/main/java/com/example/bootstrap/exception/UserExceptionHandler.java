package com.example.bootstrap.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<UserIncorrectdata> handleException
            (NoSuchEmployeeException noSuchEmployeeException) {
        UserIncorrectdata userIncorrectdata = new UserIncorrectdata();
        userIncorrectdata.setText(noSuchEmployeeException.getMessage());
        return new ResponseEntity<>(userIncorrectdata, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<UserIncorrectdata> handleException
            (Exception exception) {
        UserIncorrectdata userIncorrectdata = new UserIncorrectdata();
        userIncorrectdata.setText(exception.getMessage());
        return new ResponseEntity<>(userIncorrectdata, HttpStatus.NOT_FOUND);
    }
}
