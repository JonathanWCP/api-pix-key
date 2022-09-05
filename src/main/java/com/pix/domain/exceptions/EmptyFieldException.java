package com.pix.domain.exceptions;

import br.com.fluentvalidator.context.ValidationResult;
import br.com.fluentvalidator.exception.ValidationException;

public class EmptyFieldException extends ValidationException {
    protected EmptyFieldException(ValidationResult validationResult) {
        super(validationResult);
    }
}
