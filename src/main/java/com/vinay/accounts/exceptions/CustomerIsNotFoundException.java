package com.vinay.accounts.exceptions;

public class CustomerIsNotFoundException extends RuntimeException {
    public CustomerIsNotFoundException(String message) {
        super(message);
    }
}
