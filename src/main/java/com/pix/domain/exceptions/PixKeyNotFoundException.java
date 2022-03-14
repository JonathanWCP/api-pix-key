package com.pix.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class PixKeyNotFoundException extends Exception {
    public PixKeyNotFoundException(String message) {
        super(message);
    }
}
