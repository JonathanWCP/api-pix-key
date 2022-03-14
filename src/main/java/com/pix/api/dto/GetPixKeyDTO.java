package com.pix.api.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class GetPixKeyDTO {
    private String keyType;
    private BigDecimal agencyNumber;
    private BigDecimal accountNumber;
    private String accountHolderName;
    private Date datetimeInclusion;
    private Date datetimeInactivation;
}
