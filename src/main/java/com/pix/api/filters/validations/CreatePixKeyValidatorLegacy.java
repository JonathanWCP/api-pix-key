package com.pix.api.filters.validations;

import com.pix.domain.exceptions.EmptyFieldException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CreatePixKeyValidatorLegacy {

    public void checkKeyType(String keyType) throws Exception {
        if (stringIsNullOrEmpty(keyType))
            throw new Exception("Key type must not be null!");

        if (!keyType.equals("celular") && !keyType.equals("email") && !keyType.equals("cpf")
                && !keyType.equals("cnpj") && !keyType.equals("aleatorio"))
            throw new Exception("Invalid Key type! Must be 'celular', 'email', 'cpf', 'cnpj' or 'aleatorio'");
    }

    public void checkAccountType(String accountType) throws Exception {
        if (stringIsNullOrEmpty(accountType))
            throw new Exception("Account type should not be null or empty!");

        if (accountType.length() > 10)
            throw new Exception("Account type must have a maximum length of 10 characters");

        if (!accountType.equals("corrente") && !accountType.equals("poupanca"))
            throw new Exception("Invalid account type values! Must be 'corrente' or 'poupanca'");
    }

    public void checkAgencyNumber(BigDecimal agencyNumber) throws Exception {
        if (bigDecimalIsNullOrZero(agencyNumber))
            throw new Exception("Agency number should not be null!");

        if (String.valueOf(agencyNumber).length() > 4)
            throw new Exception("Agency number must have a maximum length of 4 digits");
    }

    public void checkAccountNumber(BigDecimal accountNumber) throws Exception {
        if (bigDecimalIsNullOrZero(accountNumber))
            throw new Exception("Account Number should not be null!");

        if (String.valueOf(accountNumber).length() > 8)
            throw new Exception("Account number must have a maximum length of 8 digits");
    }

    public void checkGenericKeyValue(String keyValue) throws Exception {
        if (stringIsNullOrEmpty(keyValue))
            throw new Exception("Key value must not be null or empty!");
    }

    public void checkTelephoneNumber(String telephoneNumber) throws Exception {
        if (!telephoneNumber.contains("+55"))
            throw new Exception("Telephone should contain '+55' country code!");

        boolean keyTypeHasLetters = checkIfStringContainLetters(telephoneNumber);
        if (keyTypeHasLetters)
                throw new Exception("Telephone must contain only numbers!");

        if (telephoneNumber.length() < 15)
            throw new Exception("Telephone must have a maximum length of 15 digits! Ex: +55011965463216");
    }

    public void checkEmail(String email) throws Exception {
        if (!email.contains("@"))
            throw new Exception("Email must contain @ character!");

        if (email.length() > 77)
            throw new Exception("Email length must have a maximum length of 77 characters");
    }

    public void checkCPF(String cpf) throws Exception {
        if (cpf.length() != 11)
            throw new Exception("CPF length must contain 11 digits!");
    }

    public void checkCNPJ(String cnpj) throws Exception {
        if (cnpj.length() != 14)
            throw new Exception("CNPJ length must contain 14 digits!");
    }

    public void checkRandomKey(String randomKey) throws Exception {
        if (randomKey.length() > 36)
            throw new Exception("Random key must have a maximum length of 36 characters");
    }

    public void checkPersonType(String personType) throws Exception {
        if (stringIsNullOrEmpty(personType))
            throw new Exception("Person type must not be null or empty!");

        if (!personType.equals("fisica") && !personType.equals("juridica"))
            throw new Exception("Person type must be 'fisica' or 'juridica'");
    }

    public void checkAccountHolderName(String name) throws Exception {
        if (stringIsNullOrEmpty(name))
            throw new Exception("Account holder name must not be null or empty!");

        if (name.length() > 30)
            throw new Exception("Account holder name must have a maximum length of 30 characters");
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
