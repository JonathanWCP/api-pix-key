package com.pix.api.filters.validations;

import com.pix.domain.exceptions.ValidationException;

public class CreatePixKeyValidator {

    public void checkAccountType(String accountType) throws ValidationException {
        if (accountType == null || accountType == "")
            throw new ValidationException("Account type should not be null or empty!");

        if (accountType.length() > 10)
            throw new ValidationException("Account type must have a maximum length of 10 characters");

        if (accountType != "corrente" || accountType != "poupanca")
            throw new ValidationException("Invalid account type values! Must be 'corrente' or 'poupanca'");
    }


}
