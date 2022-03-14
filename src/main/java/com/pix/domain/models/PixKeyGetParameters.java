package com.pix.domain.models;

import java.math.BigDecimal;
import java.sql.Date;
import lombok.Data;

@Data
public class PixKeyGetParameters {
    private String id;
    private String keyType;
    private BigDecimal agencyNumber;
    private BigDecimal accountNumber;
    private String accountHolderName;
    private String accountHolderLastName;
    private Date datetimeInclusion;
    private Date datetimeInactivation;
}
