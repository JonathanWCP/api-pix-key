package com.pix;

import com.pix.domain.models.PixKey;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MockBuilder {

    public PixKey GeneratePixKeyObject() {
        PixKey pixKey = new PixKey();
        pixKey.setId(UUID.randomUUID().toString());
        pixKey.setKeyType("telefone");
        pixKey.setKeyValue("+5511999999999");
        pixKey.setAgencyNumber(new BigDecimal(1665));
        pixKey.setAccountType("corrente");
        pixKey.setAccountNumber(new BigDecimal(54635216));
        pixKey.setAccountHolderName("Jonathan Willian");
        pixKey.setAccountHolderLastName("Castro Pinheiro");
        pixKey.setPersonType("fisica");
        pixKey.setDatetimeInclusion(new Date(System.currentTimeMillis()));
        return pixKey;
    }

    public List<PixKey> GeneratePixKeyListObject() {
        List<PixKey> pixKeys = new ArrayList<>();
        pixKeys.add(GeneratePixKeyObject());
        pixKeys.add(GeneratePixKeyObject());
        pixKeys.add(GeneratePixKeyObject());
        return pixKeys;
    }
}
