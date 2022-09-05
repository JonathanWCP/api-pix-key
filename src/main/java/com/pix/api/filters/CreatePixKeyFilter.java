package com.pix.api.filters;

import com.pix.api.dto.CreatePixKeyDTO;
import com.pix.api.filters.validations.CreatePixKeyValidator;
import com.pix.api.filters.validations.CreatePixKeyValidatorLegacy;
import com.pix.domain.exceptions.EmptyFieldException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatePixKeyFilter {

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

        createPixKeyValidator.validate(createPixKeyDTO).isInvalidThrow(EmptyFieldException.class);

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
}
