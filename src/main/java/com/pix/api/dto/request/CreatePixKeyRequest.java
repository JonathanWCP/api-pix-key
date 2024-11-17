package com.pix.api.dto.request;

import com.pix.api.dto.validation.annotation.KeyType;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public record CreatePixKeyRequest(

        @NotBlank(message = "Key type must not be null!")
        @KeyType(value = {"celular", "email", "cpf", "cnpj", "aleatorio"}, message = "Invalid Key type! Must be 'celular', 'email', 'cpf', 'cnpj' or 'aleatorio'")
        String keyType,

        String keyValue,

        BigDecimal agencyNumber,

        String accountType,

        BigDecimal accountNumber,

        String accountHolderName,

        String accountHolderLastName,

        String personType) {
}
