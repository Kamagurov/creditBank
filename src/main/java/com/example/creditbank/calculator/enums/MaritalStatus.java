package com.example.creditbank.calculator.enums;

import lombok.Getter;

@Getter
public enum MaritalStatus {

    SINGLE("Холост"),
    DIVORCED("Разведен/разведена"),
    MARRIED("Замужем/женат");

    private final String status;

    MaritalStatus(String status) {
        this.status = status;
    }

}
