package com.pix.domain.models;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import java.sql.Date;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "PIX_KEY")
public class PixKey {

    @Id
    @NotEmpty
    private String id;
    private String keyType;
    private String keyValue;
    private int agencyNumber;
    private String accountType;
    private int accountNumber;
    private String accountHolderName;
    private String accountHolderLastName;
    private String personType;
    private Date datetimeInclusion;
    private Date datetimeInactivation;
}
