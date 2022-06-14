package com.pix.api.dto;

import com.pix.api.enums.PixKeyType;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class CreatePixKeyDTO {

    private PixKeyType keyType;
    private String keyValue;
    private BigDecimal agencyNumber;
    private String accountType;
    private BigDecimal accountNumber;
    private String accountHolderName;
    private String accountHolderLastName;
    private String personType;
}
