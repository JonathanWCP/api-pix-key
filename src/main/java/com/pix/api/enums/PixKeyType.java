package com.pix.api.enums;

public enum PixKeyType {
    CELL("celular"),
    EMAIL("email"),
    CPF("cpf"),
    CNPJ("cnpj"),
    RANDOM("aleatorio");

    private final String keyType;

    PixKeyType(String keyType) {
        this.keyType = keyType;
    }

    public String toString() {
        return keyType;
    }
}
