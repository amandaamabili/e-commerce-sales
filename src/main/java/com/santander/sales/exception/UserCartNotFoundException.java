package com.santander.sales.exception;


import org.springframework.http.HttpStatus;

public class UserCartNotFoundException extends RuntimeException {
    static final String message = "User:%s doesn't have any cart";
    final HttpStatus code;

    public UserCartNotFoundException(String userId) {
        super(String.format(message, userId));
        this.code = HttpStatus.NOT_FOUND;
    }
}