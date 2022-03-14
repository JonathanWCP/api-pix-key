package com.pix.domain.services;

import com.pix.api.dto.CreatePixKeyDTO;
import com.pix.api.dto.GetPixKeyDTO;
import com.pix.api.dto.UpdatePixKeyDTO;
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
        pixKey.setAgencyNumber(new BigDecimal(1325));
        pixKey.setAccountType("corrente");
        pixKey.setAccountNumber(new BigDecimal(54635216));
        pixKey.setAccountHolderName("Jonathan Willian");
        pixKey.setAccountHolderLastName("Castro Pinheiro");
        pixKey.setDatetimeInclusion(new Date(System.currentTimeMillis()));
        return pixKey;
    }

    public CreatePixKeyDTO GenerateCreatePixKeyObject() {
        CreatePixKeyDTO createPixKeyDTO = new CreatePixKeyDTO();
        createPixKeyDTO.setKeyType("telefone");
        createPixKeyDTO.setKeyValue("+5511999999999");
        createPixKeyDTO.setAgencyNumber(new BigDecimal(1325));
        createPixKeyDTO.setAccountType("corrente");
        createPixKeyDTO.setAccountNumber(new BigDecimal(54635216));
        createPixKeyDTO.setAccountHolderName("Jonathan Willian");
        createPixKeyDTO.setAccountHolderLastName("Castro Pinheiro");
        return createPixKeyDTO;
    }

    public UpdatePixKeyDTO GenerateUpdatePixKeyDTOObject() {
        UpdatePixKeyDTO updatePixKeyDTO = new UpdatePixKeyDTO();
        updatePixKeyDTO.setId(UUID.randomUUID().toString());
        updatePixKeyDTO.setAccountType("corrente");
        updatePixKeyDTO.setAgencyNumber(new BigDecimal(1650));
        updatePixKeyDTO.setAccountNumber(new BigDecimal(1464834));
        updatePixKeyDTO.setAccountHolderName("Jonathan Willian");
        updatePixKeyDTO.setAccountHolderLastName("Castro Pinheiro");
        return updatePixKeyDTO;
    }

    public GetPixKeyDTO GenerateGetPixKeyDTOObject() {
        GetPixKeyDTO getPixKeyDTO = new GetPixKeyDTO();
        getPixKeyDTO.setKeyType("telefone");
        getPixKeyDTO.setAgencyNumber(new BigDecimal(1325));
        getPixKeyDTO.setAccountNumber(new BigDecimal(54635216));
        getPixKeyDTO.setAccountHolderName("Jonathan Willian");
        getPixKeyDTO.setDatetimeInclusion(new Date(System.currentTimeMillis()));
        return getPixKeyDTO;
    }

    public List<PixKey> GeneratePixKeyListObject() {
        List<PixKey> pixKeys = new ArrayList<>();
        pixKeys.add(GeneratePixKeyObject());
        pixKeys.add(GeneratePixKeyObject());
        pixKeys.add(GeneratePixKeyObject());
        return pixKeys;
    }
}
