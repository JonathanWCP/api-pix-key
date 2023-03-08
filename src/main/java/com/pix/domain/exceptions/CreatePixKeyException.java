package com.pix.domain.exceptions;

import br.com.fluentvalidator.context.ValidationResult;
import br.com.fluentvalidator.exception.ValidationException;

public class CreatePixKeyException extends ValidationException {
    public CreatePixKeyException(ValidationResult validationResult) {
        super(validationResult);
    }
}
