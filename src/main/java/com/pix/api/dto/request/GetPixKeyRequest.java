package com.pix.api.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
public class GetPixKeyRequest {

    private String keyType;
    private BigDecimal agencyNumber;
    private BigDecimal accountNumber;
    private String accountHolderName;
    private Date datetimeInclusion;
    private Date datetimeInactivation;

}
