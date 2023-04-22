package com.pix.api.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.math.BigDecimal;

@Getter
@Setter
public class UpdatePixKeyRequest {

    private String id;
    private String accountType;
    private BigDecimal agencyNumber;
    private BigDecimal accountNumber;
    private String accountHolderName;
    private String accountHolderLastName;

}
