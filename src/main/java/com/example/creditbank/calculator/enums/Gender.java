package com.example.creditbank.calculator.enums;

import lombok.Getter;

@Getter
public enum Gender {

    MALE("Мужчина"), FEMALE("Женщина");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }
}
