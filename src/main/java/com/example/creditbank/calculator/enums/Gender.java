package com.example.creditbank.calculator.enums;

import lombok.Getter;

@Getter
public enum Gender {

    MALE("Мужчина"),
    FEMALE("Женщина"),
    NON_BINARY("Не бинарный");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }
}
