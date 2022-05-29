package com.santander.sales.exception;


import org.springframework.http.HttpStatus;

public class UserHasAlreadyCartException extends RuntimeException {
    static final String message = "User has already cart";
    final HttpStatus code;

    public UserHasAlreadyCartException() {
        super(message);
        this.code = HttpStatus.BAD_REQUEST;
    }
}