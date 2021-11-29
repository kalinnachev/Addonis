package com.telerikacademy.addonis.contollers.aspects;

import com.telerikacademy.addonis.exceptions.DuplicateEntityException;
import com.telerikacademy.addonis.exceptions.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;

/*
https://auth0.com/blog/get-started-with-custom-error-handling-in-spring-boot-java/
 */

@ControllerAdvice("com.telerikacademy.addonis.contollers.rest")
public class RestControllerAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(EntityNotFoundException e) {
       return generateResponse(HttpStatus.NOT_FOUND, e);
    }

    @ExceptionHandler(DuplicateEntityException.class)
    public ResponseEntity<ErrorResponse> handleException(DuplicateEntityException e) {
        return generateResponse(HttpStatus.CONFLICT, e);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        return generateResponseWithStackTrace(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    private String getStackTrace(Exception e){
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        String stackTrace = stringWriter.toString();
        return stackTrace;
    }

    private ResponseEntity<ErrorResponse> generateResponseWithStackTrace
            (HttpStatus status, Exception e){
        ErrorResponse errorResponse =
                new ErrorResponse(status, e.getMessage(), getStackTrace(e));
        return new ResponseEntity<>(errorResponse, status);
    }

    private ResponseEntity<ErrorResponse> generateResponse
            (HttpStatus status, Exception e){
        ErrorResponse errorResponse =
                new ErrorResponse(status, e.getMessage());
        return new ResponseEntity<>(errorResponse, status);
    }
}
