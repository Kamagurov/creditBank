package com.example.creditbank.deal.enums;

import lombok.Getter;

@Getter
public enum ChangeType {

    AUTOMATIC("Автоматический"),
    MANUAL("Ручной");

    private final String type;

    ChangeType(String type) {
        this.type = type;
    }
}
