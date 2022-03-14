package com.pix.api.filtersTests;

import com.pix.api.dto.CreatePixKeyDTO;
import com.pix.api.filters.CreatePixKeyFilter;
import com.pix.domain.exceptions.InvalidCpfException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreatePixKeyFilterTests {

    @Autowired
    private CreatePixKeyFilter unitUnderTests;

    @Test
    public void Should_ValidateCpf_WithSuccess_When_CpfIsValid() {
        // Arrange
        CreatePixKeyDTO validCpf = mockPixKeyObject("45453716800");

        // Act and Assert
        assertDoesNotThrow(() -> unitUnderTests.Validate(validCpf));
    }

    @Test
    public void Should_ThrowInvalidCpfException_When_CpfIsInvalid() {
        // Arrange
        CreatePixKeyDTO validCpf = mockPixKeyObject("11111111111");

        // Act and Assert
        assertThrows(InvalidCpfException.class, () -> unitUnderTests.Validate(validCpf));
    }

    private CreatePixKeyDTO mockPixKeyObject(String keyType) {
        CreatePixKeyDTO mockObj = new CreatePixKeyDTO();
        mockObj.setKeyType(keyType);
        return mockObj;
    }
}
