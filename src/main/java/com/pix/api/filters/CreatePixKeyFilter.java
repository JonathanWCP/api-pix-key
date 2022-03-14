package com.pix.api.filters;

import com.pix.api.dto.CreatePixKeyDTO;
import com.pix.api.filters.validations.CreatePixKeyValidator;
import com.pix.domain.exceptions.InvalidCpfException;
import com.pix.domain.exceptions.ValidationException;
import com.pix.domain.services.IPixService;
import com.pix.domain.services.implementations.PixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
public class CreatePixKeyFilter implements ICommandValidator<CreatePixKeyDTO> {

    @Configuration
    static class CreatePixKeyFilterContextConfiguration {
        @Bean
        public CreatePixKeyValidator createPixValidator() {
            return new CreatePixKeyValidator();
        }
    }

    @Autowired
    CreatePixKeyValidator validator;

    @Override
    public void Validate(CreatePixKeyDTO createPixKeyDTO) throws ValidationException {

        validator.checkAccountType(createPixKeyDTO.getAccountType());

    }
}
