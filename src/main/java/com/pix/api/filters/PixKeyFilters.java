package com.pix.api.filters;

import com.pix.api.dto.CreatePixKeyDTO;
import com.pix.api.dto.GetPixKeyDTO;
import com.pix.api.dto.UpdatePixKeyDTO;
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

    public void Validate(CreatePixKeyDTO createPixKeyDTO) throws Exception {
        final String celularKeyTypeValue = "celular";
        final String emailKeyTypeValue = "email";
        final String cpfKeyTypeValue = "cpf";
        final String cnpjKeyTypeValue = "cnpj";
        final String aleatorioKeyTypeValue = "aleatorio";

        validator.checkGenericKeyValue(createPixKeyDTO.getKeyValue());
        validator.checkAccountType(createPixKeyDTO.getAccountType());
        validator.checkAgencyNumber(createPixKeyDTO.getAgencyNumber());
        validator.checkAccountNumber(createPixKeyDTO.getAccountNumber());
        validator.checkPersonType(createPixKeyDTO.getPersonType());

        if (createPixKeyDTO.getKeyType().equals(celularKeyTypeValue))
            validator.checkTelephoneNumber(createPixKeyDTO.getKeyValue());

        if (createPixKeyDTO.getKeyType().equals(emailKeyTypeValue))
            validator.checkEmail(createPixKeyDTO.getKeyValue());

        if (createPixKeyDTO.getKeyType().equals(cpfKeyTypeValue))
            validator.checkCPF(createPixKeyDTO.getKeyValue());

        if (createPixKeyDTO.getKeyType().equals(cnpjKeyTypeValue))
            validator.checkCNPJ(createPixKeyDTO.getKeyValue());

        if (createPixKeyDTO.getKeyType().equals(aleatorioKeyTypeValue))
            validator.checkRandomKey(createPixKeyDTO.getKeyValue());
    }

    @Override
    public void CreatePixKeyValidator(CreatePixKeyDTO createPixKeyDTO) {
        createPixKeyValidator.validate(createPixKeyDTO).isInvalidThrow(CreatePixKeyException.class);
    }

    @Override
    public void DeletePixKeyValidator(String id) {

    }

    @Override
    public void GetPixKeyValidator(GetPixKeyDTO getPixKeyDTO) {

    }

    @Override
    public void GetPixKeysFilteredValidator(String keyType, BigDecimal agencyNumber, BigDecimal accountNumber, String accountHolderName, Date datetimeInclusion, Date datetimeInactivation) {

    }

    @Override
    public void UpdatePixKeyValidator(UpdatePixKeyDTO updatePixKeyDTO) {

    }
}
