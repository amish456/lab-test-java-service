package com.labtest.lab.test.java.service.exception;

public class MediaNotFoundException extends RuntimeException{

    public MediaNotFoundException(String message) {
        super(message);
    }
}
