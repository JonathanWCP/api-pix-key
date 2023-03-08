package com.pix.domain.models;

import lombok.Data;

@Data
public class ExceptionResponseJSONMessage {

    public ExceptionResponseJSONMessage(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public ExceptionResponseJSONMessage(int statusCode, String message, String fieldWithError) {
        this.statusCode = statusCode;
        this.message = message;
        this.field = fieldWithError;
    }

    private int statusCode;
    private String message;
    private String field;
}
