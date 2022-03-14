package com.pix.domain.exceptions;

public class PixKeyLimitReachedException extends Exception {
    public PixKeyLimitReachedException(String message) {
        super(message);
    }
}
