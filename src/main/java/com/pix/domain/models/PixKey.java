package com.pix.domain.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "PIX_KEY")
public class PixKey {

    @Id
    @NotEmpty
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
