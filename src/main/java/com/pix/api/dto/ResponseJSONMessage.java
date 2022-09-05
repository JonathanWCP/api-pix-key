package com.pix.api.dto;

import lombok.Data;

@Data
public class ResponseJSONMessage {
    String statusCode;
    Object objects;
}
