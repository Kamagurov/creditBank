package com.example.creditbank.deal.enums;

import lombok.Getter;

@Getter
public enum CreditStatus {

    CALCULATED("Высчитывается"),
    ISSUED("Выдан");

    private final String creditStatus;

    CreditStatus(String creditStatus) {
        this.creditStatus = creditStatus;
    }
}
