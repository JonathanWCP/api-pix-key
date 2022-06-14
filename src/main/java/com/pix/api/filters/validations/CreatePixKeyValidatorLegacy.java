package com.pix.api.filters.validations;

import com.pix.domain.exceptions.ValidationException;

import java.math.BigDecimal;

public class CreatePixKeyValidatorLegacy {

    public void checkKeyType(String keyType) throws ValidationException {
        if (stringIsNullOrEmpty(keyType))
            throw new ValidationException("Key type must not be null!");

        if (!keyType.equals("celular") && !keyType.equals("email") && !keyType.equals("cpf")
                && !keyType.equals("cnpj") && !keyType.equals("aleatorio"))
            throw new ValidationException("Invalid Key type! Must be 'celular', 'email', 'cpf', 'cnpj' or 'aleatorio'");
    }

    public void checkAccountType(String accountType) throws ValidationException {
        if (stringIsNullOrEmpty(accountType))
            throw new ValidationException("Account type should not be null or empty!");

        if (accountType.length() > 10)
            throw new ValidationException("Account type must have a maximum length of 10 characters");

        if (!accountType.equals("corrente") && !accountType.equals("poupanca"))
            throw new ValidationException("Invalid account type values! Must be 'corrente' or 'poupanca'");
    }

    public void checkAgencyNumber(BigDecimal agencyNumber) throws ValidationException {
        if (bigDecimalIsNullOrZero(agencyNumber))
            throw new ValidationException("Agency number should not be null!");

        if (String.valueOf(agencyNumber).length() > 4)
            throw new ValidationException("Agency number must have a maximum length of 4 digits");
    }

    public void checkAccountNumber(BigDecimal accountNumber) throws ValidationException {
        if (bigDecimalIsNullOrZero(accountNumber))
            throw new ValidationException("Account Number should not be null!");

        if (String.valueOf(accountNumber).length() > 8)
            throw new ValidationException("Account number must have a maximum length of 8 digits");
    }

    public void checkGenericKeyValue(String keyValue) throws ValidationException {
        if (stringIsNullOrEmpty(keyValue))
            throw new ValidationException("Key value must not be null or empty!");
    }

    public void checkTelephoneNumber(String telephoneNumber) throws ValidationException {
        if (!telephoneNumber.contains("+55"))
            throw new ValidationException("Telephone should contain '+55' country code!");

        boolean keyTypeHasLetters = checkIfStringContainLetters(telephoneNumber);
        if (keyTypeHasLetters)
                throw new ValidationException("Telephone must contain only numbers!");

        if (telephoneNumber.length() < 15)
            throw new ValidationException("Telephone must have a maximum length of 15 digits! Ex: +55011965463216");
    }

    public void checkEmail(String email) throws ValidationException {
        if (!email.contains("@"))
            throw new ValidationException("Email must contain @ character!");

        if (email.length() > 77)
            throw new ValidationException("Email length must have a maximum length of 77 characters");
    }

    public void checkCPF(String cpf) throws ValidationException {
        if (cpf.length() != 11)
            throw new ValidationException("CPF length must contain 11 digits!");
    }

    public void checkCNPJ(String cnpj) throws ValidationException {
        if (cnpj.length() != 14)
            throw new ValidationException("CNPJ length must contain 14 digits!");
    }

    public void checkRandomKey(String randomKey) throws ValidationException {
        if (randomKey.length() > 36)
            throw new ValidationException("Random key must have a maximum length of 36 characters");
    }

    public void checkPersonType(String personType) throws ValidationException {
        if (stringIsNullOrEmpty(personType))
            throw new ValidationException("Person type must not be null or empty!");

        if (!personType.equals("fisica") && !personType.equals("juridica"))
            throw new ValidationException("Person type must be 'fisica' or 'juridica'");
    }

    public void checkAccountHolderName(String name) throws ValidationException {
        if (stringIsNullOrEmpty(name))
            throw new ValidationException("Account holder name must not be null or empty!");

        if (name.length() > 30)
            throw new ValidationException("Account holder name must have a maximum length of 30 characters");
    }

    private boolean stringIsNullOrEmpty(String text) {
        return text == null || text.equals("");
    }

    private boolean bigDecimalIsNullOrZero(BigDecimal number) {
        return number == null || number.equals(new BigDecimal(0));
    }

    private boolean checkIfStringContainLetters(String text){
        char[] chars = text.toCharArray();
        for(char c : chars)
            if(Character.isLetter(c)){
                return true;
            }

        return false;
    }
}
