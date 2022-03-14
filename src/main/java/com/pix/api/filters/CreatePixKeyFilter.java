package com.pix.api.filters;

import com.pix.api.dto.CreatePixKeyDTO;
import com.pix.api.filters.validations.CreatePixKeyValidator;
import com.pix.domain.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
public class CreatePixKeyFilter {

    @Configuration
    static class CreatePixKeyFilterContextConfiguration {
        @Bean
        public CreatePixKeyValidator createPixValidator() {
            return new CreatePixKeyValidator();
        }
    }

    @Qualifier("createPixValidator")
    @Autowired
    CreatePixKeyValidator validator;

    public void Validate(CreatePixKeyDTO createPixKeyDTO) throws ValidationException {
        validator.checkKeyType(createPixKeyDTO.getKeyType());
        validator.checkGenericKeyValue(createPixKeyDTO.getKeyValue());
        validator.checkAccountType(createPixKeyDTO.getAccountType());
        validator.checkAgencyNumber(createPixKeyDTO.getAgencyNumber());
        validator.checkAccountNumber(createPixKeyDTO.getAccountNumber());
        validator.checkPersonType(createPixKeyDTO.getPersonType());

        if (createPixKeyDTO.getKeyType().equals("celular"))
            validator.checkTelephoneNumber(createPixKeyDTO.getKeyValue());

        if (createPixKeyDTO.getKeyType().equals("email"))
            validator.checkEmail(createPixKeyDTO.getKeyValue());

        if (createPixKeyDTO.getKeyType().equals("cpf"))
            validator.checkCPF(createPixKeyDTO.getKeyValue());

        if (createPixKeyDTO.getKeyType().equals("cnpj"))
            validator.checkCNPJ(createPixKeyDTO.getKeyValue());

        if (createPixKeyDTO.getKeyType().equals("aleatorio"))
            validator.checkRandomKey(createPixKeyDTO.getKeyValue());
    }
}
