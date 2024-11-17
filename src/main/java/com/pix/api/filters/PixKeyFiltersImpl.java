package com.pix.api.filters;

import com.pix.api.dto.request.CreatePixKeyRequest;
import com.pix.api.dto.request.GetPixKeyRequest;
import com.pix.api.dto.request.UpdatePixKeyRequest;
import com.pix.api.filters.validations.CreatePixKeyValidator;
import com.pix.api.filters.validations.CreatePixKeyValidatorLegacy;
import com.pix.domain.exceptions.CreatePixKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;

@Component
public class PixKeyFiltersImpl implements PixKeyFilters {

    @Autowired
    CreatePixKeyValidatorLegacy validator;

    @Autowired
    CreatePixKeyValidator createPixKeyValidator;

    public void Validate(CreatePixKeyRequest createPixKeyRequest) throws Exception {
        final String celularKeyTypeValue = "celular";
        final String emailKeyTypeValue = "email";
        final String cpfKeyTypeValue = "cpf";
        final String cnpjKeyTypeValue = "cnpj";
        final String aleatorioKeyTypeValue = "aleatorio";

        validator.checkGenericKeyValue(createPixKeyRequest.keyValue());
        validator.checkAccountType(createPixKeyRequest.accountType());
        validator.checkAgencyNumber(createPixKeyRequest.agencyNumber());
        validator.checkAccountNumber(createPixKeyRequest.accountNumber());
        validator.checkPersonType(createPixKeyRequest.personType());

        if (createPixKeyRequest.keyType().equals(celularKeyTypeValue))
            validator.checkTelephoneNumber(createPixKeyRequest.keyValue());

        if (createPixKeyRequest.keyType().equals(emailKeyTypeValue))
            validator.checkEmail(createPixKeyRequest.keyValue());

        if (createPixKeyRequest.keyType().equals(cpfKeyTypeValue))
            validator.checkCPF(createPixKeyRequest.keyValue());

        if (createPixKeyRequest.keyType().equals(cnpjKeyTypeValue))
            validator.checkCNPJ(createPixKeyRequest.keyValue());

        if (createPixKeyRequest.keyType().equals(aleatorioKeyTypeValue))
            validator.checkRandomKey(createPixKeyRequest.keyValue());
    }

    @Override
    public void CreatePixKeyValidator(CreatePixKeyRequest createPixKeyRequest) {
        createPixKeyValidator.validate(createPixKeyRequest).isInvalidThrow(CreatePixKeyException.class);
    }

    @Override
    public void DeletePixKeyValidator(String id) {

    }

    @Override
    public void GetPixKeyValidator(GetPixKeyRequest getPixKeyRequest) {

    }

    @Override
    public void GetPixKeysFilteredValidator(String keyType, BigDecimal agencyNumber, BigDecimal accountNumber, String accountHolderName, Date datetimeInclusion, Date datetimeInactivation) {

    }

    @Override
    public void UpdatePixKeyValidator(UpdatePixKeyRequest updatePixKeyRequest) {

    }
}
