package com.pix.domain.models;

import lombok.Data;

@Data
public class ExceptionResponseJSONMessage {
    private int statusCode;
    private String message;
}
