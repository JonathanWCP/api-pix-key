package com.pix.api.enums;

public enum PixKeyType {
    CELL("celular"),
    EMAIL("email"),
    CPF("cpf"),
    CNPJ("cnpj"),
    RANDOM("aleatorio");

    private String keyType;

    PixKeyType(String keyType) {
        this.keyType = keyType;
    }

    public String getValue() {
        return keyType;
    }
}
