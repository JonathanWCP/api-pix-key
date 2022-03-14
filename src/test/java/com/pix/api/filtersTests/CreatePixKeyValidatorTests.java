package com.pix.api.filtersTests;

import com.pix.MockBuilder;
import com.pix.api.filters.validations.CreatePixKeyValidator;
import com.pix.domain.exceptions.ValidationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CreatePixKeyValidatorTests {

    @TestConfiguration
    static class CreatePixKeyFilterTestContextConfiguration {
        @Bean
        public CreatePixKeyValidator createPixKeyFilter() { return new CreatePixKeyValidator(); }

        @Bean
        public MockBuilder mockBuilder() {return new MockBuilder(); }
    }

    @Autowired
    private CreatePixKeyValidator unitUnderTests;

    @Test
    public void Should_ValidateKeyType_WithSuccess_When_KeyTypeIsValid() throws ValidationException {
        // Arrange
        String[] validKeyTypes = { "celular", "email", "cpf", "cnpj", "aleatorio" };

        // Act and Assert
        for(String keyType : validKeyTypes)
            unitUnderTests.checkKeyType(keyType);
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
        Assert.assertThrows(ValidationException.class,
                () -> unitUnderTests.checkAccountType(null));
    }

    @Test
    public void Should_ThrowValidationException_When_AccountTypeIsEmptyString() {
        // Act and Assert
        Assert.assertThrows(ValidationException.class,
                () -> unitUnderTests.checkAccountType(""));
    }

    @Test
    public void Should_ThrowValidationException_When_AccountTypeLengthIsGreaterThan10() {
        // Arrange
        String accountType = "correnteeee";

        // Act and Assert
        Assert.assertThrows(ValidationException.class,
                () -> unitUnderTests.checkAccountType(accountType));
    }

    @Test
    public void Should_ThrowValidationException_When_AccountTypeIsNotPoupancaOrCorrente() {
        // Arrange
        String accountType = "salario";

        // Act and Assert
        Assert.assertThrows(ValidationException.class,
                () -> unitUnderTests.checkAccountType(accountType));
    }

    @Test
    public void Should_ValidateAgencyNumber_WithSuccess_When_AgencyNumberIsValid() throws ValidationException {
        // Act and Assert
        unitUnderTests.checkAgencyNumber(1665);
    }

    @Test
    public void Should_ThrowValidationException_When_AgencyNumberIsInvalid()  {
        // Act and Assert
        Assert.assertThrows(ValidationException.class,
                () -> unitUnderTests.checkAgencyNumber(0));
    }

    @Test
    public void Should_ThrowValidationException_When_AgencyNumberIsGreaterThan4() {
        // Act and Assert
        Assert.assertThrows(ValidationException.class,
                () -> unitUnderTests.checkAgencyNumber(16655));
    }

    @Test
    public void Should_ValidateAccountNumber_WithSuccess_When_AccountNumberIsValid() throws ValidationException {
        // Act and Assert
        unitUnderTests.checkAccountNumber(16783458);
    }

    @Test
    public void Should_ThrowValidationException_When_AccountNumberIsInvalid() {
        // Act and Assert
        Assert.assertThrows(ValidationException.class,
                () -> unitUnderTests.checkAccountNumber(0));
    }

    @Test
    public void Should_ThrowValidationException_When_AccountNumberGreaterThan8() {
        // Act and Assert
        Assert.assertThrows(ValidationException.class,
                () -> unitUnderTests.checkAccountNumber(165432168));
    }

    @Test
    public void Should_ValidateKeyTypeAsTelephone_WithSuccess_When_KeyTypeIsValid() throws ValidationException {
        // Act and Assert
        unitUnderTests.checkTelephoneNumber("+55011985497217");
    }

    @Test
    public void Should_ThrowValidationException_When_TelephoneNotContainCodeArea() {
        // Act and Assert
        Assert.assertThrows(ValidationException.class,
                () -> unitUnderTests.checkTelephoneNumber("01198549721J"));
    }

    @Test
    public void Should_ThrowValidationException_When_TelephoneContainLetters() {
        // Act and Assert
        Assert.assertThrows(ValidationException.class,
                () -> unitUnderTests.checkTelephoneNumber("+5501198549721J"));
    }

    @Test
    public void Should_ThrowValidationException_When_TelephoneLengthIsLessThan15() {
        // Act and Assert
        Assert.assertThrows(ValidationException.class,
                () -> unitUnderTests.checkTelephoneNumber("+5501198549"));
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
        Assert.assertThrows(ValidationException.class,
                () -> unitUnderTests.checkEmail(email));
    }

    @Test
    public void Should_ThrowValidationException_When_EmailLengthIsBiggerThan77() {
        // Arrange
        String email = "joooooooooooooooooooooooooooonathanwc.pinheiro" +
                "@outlok.commmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm";

        // Act and Assert
        Assert.assertThrows(ValidationException.class,
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
        Assert.assertThrows(ValidationException.class,
                () -> unitUnderTests.checkCPF(cpf));
    }

    @Test
    public void Should_ThrowValidationException_When_CpfLengthIsBiggerThan11() throws ValidationException {
        // Arrange
        String cpf = "222222222556";

        // Act and Assert
        Assert.assertThrows(ValidationException.class,
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
        Assert.assertThrows(ValidationException.class,
                () -> unitUnderTests.checkCNPJ(cnpj));
    }

    @Test
    public void Should_ThrowValidationException_When_CnpjLengthIsBiggerThan14() throws ValidationException {
        // Arrange
        String cnpj = "2222222225547846";

        // Act and Assert
        Assert.assertThrows(ValidationException.class,
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
        Assert.assertThrows(ValidationException.class,
                () -> unitUnderTests.checkRandomKey(randomKey));
    }
}
