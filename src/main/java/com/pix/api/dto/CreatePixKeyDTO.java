package com.pix.api.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class CreatePixKeyDTO {

    private String keyType;
    private String keyValue;
    private BigDecimal agencyNumber;
    private String accountType;
    private BigDecimal accountNumber;
    private String accountHolderName;
    private String accountHolderLastName;
    private String personType;
}
