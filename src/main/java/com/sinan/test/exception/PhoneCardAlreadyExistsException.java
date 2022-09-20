package com.sinan.test.exception;

public class PhoneCardAlreadyExistsException extends RuntimeException {
    public PhoneCardAlreadyExistsException(String msg) {
        super(msg);
    }
}
