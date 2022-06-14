package com.pix.domain.services;

import com.pix.MockBuilder;
import com.pix.domain.exceptions.PixKeyAlreadyDisableException;
import com.pix.domain.exceptions.PixKeyAlreadyExistsException;
import com.pix.domain.exceptions.PixKeyLimitReachedException;
import com.pix.domain.exceptions.PixKeyNotFoundException;
import com.pix.domain.models.PixKey;
import com.pix.domain.repositories.PixRepository;
import com.pix.domain.services.implementations.PixService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class PixServiceTests {

    @InjectMocks
    private PixService unitUnderTests;

    @Mock
    private PixRepository mockRepository;
    private MockBuilder mockBuilder;
    private PixKey pixKey;


    @Before
    public void setUp() {
        mockBuilder = new MockBuilder();
        pixKey = mockBuilder.GeneratePixKeyObject();
        MockRepositoryBuilder();
    }

    @Test
    public void Should_CreatePixKey_When_UsingValidPixKey() throws PixKeyAlreadyExistsException, PixKeyLimitReachedException {
        // Arrange
        Mockito.when(mockRepository.findAllPixKeyByKeyValue(pixKey.getKeyValue()))
                .thenReturn(Optional.empty());
        Mockito.when(mockRepository.getAmountOfPixKey(pixKey.getAccountNumber()))
                .thenReturn(Optional.of(2));

        // Act
        unitUnderTests.CreatePixKey(pixKey);
    }

    @Test
    public void Should_ThrowPixKeyAlreadyExistsException_When_PixKeyAlreadyExists() {
        // Arrange
        Mockito.when(mockRepository.findAllPixKeyByKeyValue(pixKey.getKeyValue()))
                .thenReturn(Optional.of(2));

        // Act and Assert
        assertThrows(PixKeyAlreadyExistsException.class,
                () -> unitUnderTests.CreatePixKey(pixKey));
    }

    @Test
    public void Should_ThrowPixKeyLimitReachedException_When_LimitIsReachedForFisicaPersonType() {
        // Arrange
        Mockito.when(mockRepository.findAllPixKeyByKeyValue(pixKey.getKeyValue()))
                .thenReturn(Optional.of(0));
        Mockito.when(mockRepository.getAmountOfPixKey(pixKey.getAccountNumber()))
                .thenReturn(Optional.of(5));

        // Act and Assert
        assertThrows(PixKeyLimitReachedException.class,
                () -> unitUnderTests.CreatePixKey(pixKey));
    }

    @Test
    public void Should_ThrowPixKeyLimitReachedException_When_LimitIsReachedForJuridicaPersonType() {
        // Arrange
        pixKey.setPersonType("juridica");
        Mockito.when(mockRepository.findAllPixKeyByKeyValue(pixKey.getKeyValue()))
                .thenReturn(Optional.of(0));
        Mockito.when(mockRepository.getAmountOfPixKey(pixKey.getAccountNumber()))
                .thenReturn(Optional.of(20));

        // Act and Assert
        assertThrows(PixKeyLimitReachedException.class,
                () -> unitUnderTests.CreatePixKey(pixKey));
    }

    @Test
    public void Should_UpdatePixKey_When_UsingExistingPixKey() throws PixKeyNotFoundException, PixKeyAlreadyDisableException {
        // Arrange
        Mockito.when(mockRepository.findById(pixKey.getId()))
                .thenReturn(Optional.of(pixKey));

        // Act
        PixKey result = unitUnderTests.UpdatePixKey(pixKey);

        // Assert
        assertNotNull(result);
    }

    @Test
    public void Should_ThrowPixKeyAlreadyDisableException_When_UpdatingWithPixKeyAlreadyDisable() {
        // Arrange
        pixKey.setDatetimeInactivation(new Date(System.currentTimeMillis()));
        Mockito.when(mockRepository.findById(pixKey.getId()))
                .thenReturn(Optional.of(pixKey));

        // Act and Assert
        assertThrows(PixKeyAlreadyDisableException.class,
                () -> unitUnderTests.UpdatePixKey(pixKey));
    }

    @Test
    public void Should_ThrowPixKeyNotFoundException_When_UpdatingWithNotExistingPixKey()  {
        // Arrange
        Mockito.when(mockRepository.findById(pixKey.getId())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(PixKeyNotFoundException.class, () -> unitUnderTests.UpdatePixKey(pixKey));
    }

    @Test
    public void Should_InactivatePixKey_When_UsingExistingPixKey() throws PixKeyNotFoundException, PixKeyAlreadyDisableException {
        // Arrange
        Mockito.when(mockRepository.findById(pixKey.getId()))
                .thenReturn(Optional.of(pixKey));

        // Act
        PixKey result = unitUnderTests.InactivatePixKey(pixKey.getId());

        // Assert
        assertNotNull(result);
    }

    @Test
    public void Should_ThrowPixKeyNotFoundException_When_InactivatingWithNotExistingPixKey() {
        // Arrange
        Mockito.when(mockRepository.findById(pixKey.getId()))
                .thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(PixKeyNotFoundException.class,
                () -> unitUnderTests.InactivatePixKey(pixKey.getId()));
    }

    @Test
    public void Should_ThrowPixKeyAlreadyDisableException_When_PixKeyIsInactive() {
        // Arrange
        pixKey.setDatetimeInactivation(new Date(System.currentTimeMillis()));
        Mockito.when(mockRepository.findById(pixKey.getId()))
                .thenReturn(Optional.ofNullable(pixKey));

        // Act and Assert
        assertThrows(PixKeyAlreadyDisableException.class,
                () -> unitUnderTests.InactivatePixKey(pixKey.getId()));
    }


    @Test
    public void Should_ReturnExistingPixKey_When_UsingPixKeyId() throws PixKeyNotFoundException, PixKeyAlreadyDisableException {
        // Arrange
        Mockito.when(mockRepository.findById(pixKey.getId()))
                .thenReturn(Optional.of(pixKey));

        // Act
        unitUnderTests.GetPixKey(pixKey.getId());
    }

    @Test
    public void Should_ThrowPixKeyNotFoundException_When_GettingWithNotExistingPixKey() {
        // Arrange
        Mockito.when(mockRepository.findById(pixKey.getId()))
                .thenReturn(Optional.empty());

        // Act
        assertThrows(PixKeyNotFoundException.class,
                () -> unitUnderTests.GetPixKey(pixKey.getId()));
    }

    @Test
    public void Should_ReturnPixKeyList_When_UsingCustomRepositoryWithValidPixKey() throws PixKeyAlreadyDisableException, PixKeyNotFoundException {
        // Arrange
        Mockito.when(mockRepository.findAllPixKey(
                pixKey.getKeyType(),
                pixKey.getAgencyNumber(),
                pixKey.getAccountNumber(),
                pixKey.getAccountHolderName(),
                pixKey.getDatetimeInclusion(),
                pixKey.getDatetimeInactivation()))
                    .thenReturn(mockBuilder.GeneratePixKeyListObject());

        // Act
        List<PixKey> result = unitUnderTests.GetPixKeys(pixKey);

        // Assert
        assertNotNull(result);
    }

    @Test
    public void Should_ThrowPixKeyNotFoundException_When_UsingCustomRepositoryWithInvalidPixKey() {
        // Arrange
        List<PixKey> mockList = new ArrayList<>();
        Mockito.when(mockRepository.findAllPixKey(
                        pixKey.getKeyType(),
                        pixKey.getAgencyNumber(),
                        pixKey.getAccountNumber(),
                        pixKey.getAccountHolderName(),
                        pixKey.getDatetimeInclusion(),
                        pixKey.getDatetimeInactivation()))
                .thenReturn(mockList);

        // Act
        assertThrows(PixKeyNotFoundException.class,
                () -> unitUnderTests.GetPixKeys(pixKey));
    }

    private void MockRepositoryBuilder() {
        Mockito.when(mockRepository.saveAndFlush(pixKey))
                .thenReturn(pixKey);

        Mockito.when(mockRepository.findById(pixKey.getId()))
                .thenReturn(Optional.ofNullable(pixKey));
    }
}
