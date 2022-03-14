package com.pix.api.filters.validations;

import com.pix.domain.exceptions.ValidationException;

public class CreatePixKeyValidator {

    public void checkKeyType(String keyType) throws ValidationException {
        if (!keyType.equals("celular") && !keyType.equals("email") && !keyType.equals("cpf")
                && !keyType.equals("cnpj") && !keyType.equals("aleatorio"))
            throw new ValidationException("Invalid KeyType! Must be 'celular', 'email', 'cpf', 'cnpj' or 'aleatorio'");
    }

    public void checkAccountType(String accountType) throws ValidationException {
        if (accountType == null || accountType == "")
            throw new ValidationException("Account type should not be null or empty!");

        if (accountType.length() > 10)
            throw new ValidationException("Account type must have a maximum length of 10 characters");

        if (!accountType.equals("corrente") && !accountType.equals("poupanca"))
            throw new ValidationException("Invalid account type values! Must be 'corrente' or 'poupanca'");
    }

    public void checkAgencyNumber(int agencyNumber) throws ValidationException {
        if (agencyNumber == 0)
            throw new ValidationException("Agency number should not be null!");

        if (String.valueOf(agencyNumber).length() > 4)
            throw new ValidationException("Agency number must have a maximum length of 4 digits");
    }

    public void checkAccountNumber(int accountNumber) throws ValidationException {
        if (accountNumber == 0)
            throw new ValidationException("Account Number should not be null!");

        if (String.valueOf(accountNumber).length() > 8)
            throw new ValidationException("Account number must have a maximum length of 8 digits");
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
        if (cpf.length() < 11 || cpf.length() > 11)
            throw new ValidationException("CPF length must contain 11 digits!");
    }

    public void checkCNPJ(String cnpj) throws ValidationException {
        if (cnpj.length() < 14 || cnpj.length() > 14)
            throw new ValidationException("CNPJ length must contain 14 digits!");
    }

    public void checkRandomKey(String randomKey) throws ValidationException {
        if (randomKey.length() > 36)
            throw new ValidationException("Random key must have a maximum length of 36 characters");
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
