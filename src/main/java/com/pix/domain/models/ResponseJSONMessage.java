package com.pix.domain.models;

import lombok.Data;

@Data
public class ResponseJSONMessage {
    private int statusCode;
    private String message;
}
