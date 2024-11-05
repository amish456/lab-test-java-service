package com.labtest.lab.test.java.service.exception.handler;

import com.labtest.lab.test.java.service.entity.ErrorResponse;
import com.labtest.lab.test.java.service.exception.MediaNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {



    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ErrorResponse globalException(Exception e) {
        return new ErrorResponse(HttpStatus.BAD_GATEWAY.value(), e.getMessage());
    }

    @ExceptionHandler(MediaNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse mediaNotFoundExceptionHandler(Exception e) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

}
