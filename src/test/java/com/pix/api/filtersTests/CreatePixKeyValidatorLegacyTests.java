package com.pix.api.filtersTests;

import com.pix.api.filters.validations.CreatePixKeyValidatorLegacy;
import com.pix.domain.exceptions.ValidationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class CreatePixKeyValidatorLegacyTests {

    @InjectMocks
    private CreatePixKeyValidatorLegacy unitUnderTests;

    @Test
    public void Should_ValidateKeyType_WithSuccess_When_KeyTypeIsValid() throws ValidationException {
        // Arrange
        String[] validKeyTypes = { "celular", "email", "cpf", "cnpj", "aleatorio" };

        // Act and Assert
        for(String keyType : validKeyTypes)
            unitUnderTests.checkKeyType(keyType);
    }

    @Test
    public void Should_ThrowValidationException_When_KeyTypeIsNull() throws ValidationException {
        // Act and Assert
        assertThrows(ValidationException.class,
                () -> unitUnderTests.checkKeyType(null));
    }

    @Test
    public void Should_ThrowValidationException_When_KeyTypeIsInvalid() throws ValidationException {
        // Act and Assert
        assertThrows(ValidationException.class,
                () -> unitUnderTests.checkKeyType("telefone"));
    }

    @Test
    public void Should_ValidateAccountType_WithSuccess_When_AccountTypeIsCorrente() throws ValidationException {
        // Act and Assert
        unitUnderTests.checkAccountType("corrente");
    }

    @Test
    public void Should_ValidateAccountType_WithSuccess_When_AccountTypeIsPoupanca() throws ValidationException {
        // Act and Assert
        unitUnderTests.checkAccountType("poupanca");
    }

    @Test
    public void Should_ThrowValidationException_When_AccountTypeIsNull() {
        // Act and Assert
        assertThrows(ValidationException.class,
                () -> unitUnderTests.checkAccountType(null));
    }

    @Test
    public void Should_ThrowValidationException_When_AccountTypeIsEmptyString() {
        // Act and Assert
        assertThrows(ValidationException.class,
                () -> unitUnderTests.checkAccountType(""));
    }

    @Test
    public void Should_ThrowValidationException_When_AccountTypeLengthIsGreaterThan10() {
        // Arrange
        String accountType = "correnteeee";

        // Act and Assert
        assertThrows(ValidationException.class,
                () -> unitUnderTests.checkAccountType(accountType));
    }

    @Test
    public void Should_ThrowValidationException_When_AccountTypeIsNotPoupancaOrCorrente() {
        // Arrange
        String accountType = "salario";

        // Act and Assert
        assertThrows(ValidationException.class,
                () -> unitUnderTests.checkAccountType(accountType));
    }

    @Test
    public void Should_ValidateAgencyNumber_WithSuccess_When_AgencyNumberIsValid() throws ValidationException {
        // Act and Assert
        unitUnderTests.checkAgencyNumber(new BigDecimal(1665));
    }

    @Test
    public void Should_ThrowValidationException_When_AgencyNumberIsInvalid()  {
        // Act and Assert
        assertThrows(ValidationException.class,
                () -> unitUnderTests.checkAgencyNumber(new BigDecimal(0)));
    }

    @Test
    public void Should_ThrowValidationException_When_AgencyNumberIsGreaterThan4() {
        // Act and Assert
        assertThrows(ValidationException.class,
                () -> unitUnderTests.checkAgencyNumber(new BigDecimal(16655)));
    }

    @Test
    public void Should_ThrowValidationException_When_AgencyNumberIsNull() {
        // Act and Assert
        assertThrows(ValidationException.class,
                () -> unitUnderTests.checkAgencyNumber(null));
    }

    @Test
    public void Should_ValidateAccountNumber_WithSuccess_When_AccountNumberIsValid() throws ValidationException {
        // Act and Assert
        unitUnderTests.checkAccountNumber(new BigDecimal(16783458));
    }

    @Test
    public void Should_ThrowValidationException_When_AccountNumberIsInvalid() {
        // Act and Assert
        assertThrows(ValidationException.class,
                () -> unitUnderTests.checkAccountNumber(new BigDecimal(0)));
    }

    @Test
    public void Should_ThrowValidationException_When_AccountNumberGreaterThan8() {
        // Act and Assert
        assertThrows(ValidationException.class,
                () -> unitUnderTests.checkAccountNumber(new BigDecimal(165432168)));
    }

    @Test
    public void Should_ThrowValidationException_When_AccountNumberISNull() {
        // Act and Assert
        assertThrows(ValidationException.class,
                () -> unitUnderTests.checkAccountNumber(null));
    }

    @Test
    public void Should_ThrowValidationException_When_KeyValueIsNull() {
        // Act and Assert
        assertThrows(ValidationException.class,
                () -> unitUnderTests.checkGenericKeyValue(null));
    }

    @Test
    public void Should_ValidateKeyTypeAsTelephone_WithSuccess_When_KeyTypeIsValid() throws ValidationException {
        // Act and Assert
        unitUnderTests.checkTelephoneNumber("+55011985497217");
    }

    @Test
    public void Should_ThrowValidationException_When_TelephoneNotContainCodeArea() {
        // Act and Assert
        assertThrows(ValidationException.class,
                () -> unitUnderTests.checkTelephoneNumber("01198549721J"));
    }

    @Test
    public void Should_ThrowValidationException_When_TelephoneContainLetters() {
        // Act and Assert
        assertThrows(ValidationException.class,
                () -> unitUnderTests.checkTelephoneNumber("+5501198549721J"));
    }

    @Test
    public void Should_ThrowValidationException_When_TelephoneLengthIsLessThan15() {
        // Act and Assert
        assertThrows(ValidationException.class,
                () -> unitUnderTests.checkTelephoneNumber("+5501198549"));
    }

    @Test
    public void Should_ThrowValidationException_When_TelephoneIsZero() {
        // Act and Assert
        assertThrows(ValidationException.class,
                () -> unitUnderTests.checkTelephoneNumber("0"));
    }

    @Test
    public void Should_ValidateEmail_WithSuccess_WhenEmailIsValid() throws ValidationException {
        // Arrange
        String email = "jonathanwc.pinheiro@outlook.com";

        // Act and Assert
        unitUnderTests.checkEmail(email);
    }

    @Test
    public void Should_ThrowValidationException_When_EmailNotContainAtSign() {
        // Arrange
        String email = "jonathanwc.pinheiro";

        // Act and Assert
        assertThrows(ValidationException.class,
                () -> unitUnderTests.checkEmail(email));
    }

    @Test
    public void Should_ThrowValidationException_When_EmailLengthIsBiggerThan77() {
        // Arrange
        String email = "joooooooooooooooooooooooooooonathanwc.pinheiro" +
                "@outlok.commmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm";

        // Act and Assert
        assertThrows(ValidationException.class,
                () -> unitUnderTests.checkEmail(email));
    }

    @Test
    public void Should_ValidateCpfLength_WithSuccess_When_CpfLengthIs11() throws ValidationException {
        // Arrange
        String cpf = "22222222255";

        // Act and Assert
        unitUnderTests.checkCPF(cpf);
    }

    @Test
    public void Should_ThrowValidationException_When_CpfLengthIsLessThan11() throws ValidationException {
        // Arrange
        String cpf = "2222222225";

        // Act and Assert
        assertThrows(ValidationException.class,
                () -> unitUnderTests.checkCPF(cpf));
    }

    @Test
    public void Should_ThrowValidationException_When_CpfLengthIsBiggerThan11() throws ValidationException {
        // Arrange
        String cpf = "222222222556";

        // Act and Assert
        assertThrows(ValidationException.class,
                () -> unitUnderTests.checkCPF(cpf));
    }

    @Test
    public void Should_ValidateCnpjLength_WithSuccess_When_CnpjLengthIs14() throws ValidationException {
        // Arrange
        String cnpj = "22222222255648";

        // Act and Assert
        unitUnderTests.checkCNPJ(cnpj);
    }

    @Test
    public void Should_ThrowValidationException_When_CnpjLengthIsLessThan14() throws ValidationException {
        // Arrange
        String cnpj = "22222222";

        // Act and Assert
        assertThrows(ValidationException.class,
                () -> unitUnderTests.checkCNPJ(cnpj));
    }

    @Test
    public void Should_ThrowValidationException_When_CnpjLengthIsBiggerThan14() throws ValidationException {
        // Arrange
        String cnpj = "2222222225547846";

        // Act and Assert
        assertThrows(ValidationException.class,
                () -> unitUnderTests.checkCNPJ(cnpj));
    }

    @Test
    public void Should_ValidateRandomNumber_WithSuccess_When_RandomKeyIsValid() throws ValidationException {
        // Arrange
        String randomKey = "9831415461354535";

        // Act and Assert
        unitUnderTests.checkRandomKey(randomKey);
    }

    @Test
    public void Should_ThrowValidationException_When_RandomKeyLengthIsGreaterThan36() throws ValidationException {
        // Arrange
        String randomKey = "983141554544156464646446464685474946854164461354535";

        // Act and Assert
        assertThrows(ValidationException.class,
                () -> unitUnderTests.checkRandomKey(randomKey));
    }

    @Test
    public void Should_ValidatePersonType_WithSuccess_When_PersonTypeIsValid() throws ValidationException {
        // Arrange
        String[] personTypes = { "fisica", "juridica" };

        // Act and Assert
        for (String personType : personTypes)
            unitUnderTests.checkPersonType(personType);
    }

    @Test
    public void Should_ThrowValidationException_When_PersonTypeIsInvalid() throws ValidationException {
        // Arrange
        String personType = "humano";

        // Act and Assert
        assertThrows(ValidationException.class,
                () -> unitUnderTests.checkPersonType(personType));
    }

    @Test
    public void Should_ThrowValidationException_When_PersonTypeIsNull() throws ValidationException {
        // Act and Assert
        assertThrows(ValidationException.class,
                () -> unitUnderTests.checkPersonType(null));
    }

    @Test
    public void Should_ThrowValidationException_When_PersonTypeIsEmptyString() throws ValidationException {
        // Act and Assert
        assertThrows(ValidationException.class,
                () -> unitUnderTests.checkPersonType(""));
    }

    @Test
    public void Should_ValidateAccountHolderName_WithSuccess_When_NameIsValid() throws ValidationException {
        // Act and Assert
        unitUnderTests.checkAccountHolderName("Jonathan");
    }

    @Test
    public void Should_ThrowValidationException_When_NameIsNull() {
        // Act and Assert
        assertThrows(ValidationException.class,
                () ->  unitUnderTests.checkAccountHolderName(null));
    }

    @Test
    public void Should_ThrowValidationException_When_NameIsEmpty() {
        // Act and Assert
        assertThrows(ValidationException.class,
                () ->  unitUnderTests.checkAccountHolderName(""));
    }

    @Test
    public void Should_ThrowValidationException_When_NameLengthIsBiggerThan30() {
        // Arrange
        String name = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

        // Act and Assert
        assertThrows(ValidationException.class,
                () ->  unitUnderTests.checkAccountHolderName(name));
    }

}
