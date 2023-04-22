package com.pix.api.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
public class GetPixKeyResponse {

    private String id;
    private String keyType;
    private String keyValue;
    private BigDecimal agencyNumber;
    private String accountType;
    private BigDecimal accountNumber;
    private String accountHolderName;
    private String accountHolderLastName;
    private String personType;
    private Date datetimeInclusion;
    private Date datetimeInactivation;

}
