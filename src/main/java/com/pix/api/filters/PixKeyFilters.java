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
public class PixKeyFilters implements IPixKeyFilters {

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

        validator.checkGenericKeyValue(createPixKeyRequest.getKeyValue());
        validator.checkAccountType(createPixKeyRequest.getAccountType());
        validator.checkAgencyNumber(createPixKeyRequest.getAgencyNumber());
        validator.checkAccountNumber(createPixKeyRequest.getAccountNumber());
        validator.checkPersonType(createPixKeyRequest.getPersonType());

        if (createPixKeyRequest.getKeyType().equals(celularKeyTypeValue))
            validator.checkTelephoneNumber(createPixKeyRequest.getKeyValue());

        if (createPixKeyRequest.getKeyType().equals(emailKeyTypeValue))
            validator.checkEmail(createPixKeyRequest.getKeyValue());

        if (createPixKeyRequest.getKeyType().equals(cpfKeyTypeValue))
            validator.checkCPF(createPixKeyRequest.getKeyValue());

        if (createPixKeyRequest.getKeyType().equals(cnpjKeyTypeValue))
            validator.checkCNPJ(createPixKeyRequest.getKeyValue());

        if (createPixKeyRequest.getKeyType().equals(aleatorioKeyTypeValue))
            validator.checkRandomKey(createPixKeyRequest.getKeyValue());
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
