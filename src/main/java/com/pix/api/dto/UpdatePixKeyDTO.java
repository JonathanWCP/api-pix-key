package com.pix.api.dto;

import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;

@Data
public class UpdatePixKeyDTO {
    @Id
    private String id;
    private String accountType;
    private BigDecimal agencyNumber;
    private BigDecimal accountNumber;
    private String accountHolderName;
    private String accountHolderLastName;
}
