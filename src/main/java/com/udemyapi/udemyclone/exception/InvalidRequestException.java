package com.udemyapi.udemyclone.exception;

import lombok.Getter;

@Getter
public class InvalidRequestException extends RuntimeException {

   //private static final long serialVersionUID = 2495302465956571202L;
    private final String message;

    public InvalidRequestException(String message) {
        this.message = message;
    }

}
