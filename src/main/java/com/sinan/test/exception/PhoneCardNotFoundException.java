package com.sinan.test.exception;

public class PhoneCardNotFoundException extends RuntimeException {
    public PhoneCardNotFoundException(String msg) {
        super(msg);
    }
}
