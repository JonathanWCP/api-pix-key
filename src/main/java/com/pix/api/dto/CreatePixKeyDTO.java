package com.pix.api.dto;

import lombok.Data;

@Data
public class CreatePixKeyDTO {


    private String keyType;
    private String keyValue;
    private int agencyNumber;
    private String accountType;
    private int accountNumber;
    private String accountHolderName;
    private String accountHolderLastName;
    private String personType;
}
