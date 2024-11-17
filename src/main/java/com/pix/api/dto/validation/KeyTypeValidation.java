package com.pix.api.dto.validation;

import com.pix.api.dto.validation.annotation.KeyType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class KeyTypeValidation implements ConstraintValidator<KeyType, String> {

    private String[] allowedKeyTypes;

    @Override
    public void initialize(final KeyType constraintAnnotation) {
        this.allowedKeyTypes = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }

        return Arrays.asList(allowedKeyTypes).contains(value);
    }
}
