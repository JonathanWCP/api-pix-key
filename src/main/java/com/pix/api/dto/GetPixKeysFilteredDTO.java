package com.pix.api.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class GetPixKeysFilteredDTO {

    private String keyType;
    private BigDecimal agencyNumber;
    private BigDecimal accountNumber;
    private String accountHolderName;
    private String accountHolderLastName;
    private Date datetimeInclusion;
    private Date datetimeInactivation;

}
