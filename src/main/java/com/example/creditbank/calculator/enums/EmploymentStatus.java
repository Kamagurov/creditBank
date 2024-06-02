package com.example.creditbank.calculator.enums;

import lombok.Getter;

@Getter
public enum EmploymentStatus {

    UNEMPLOYED("Безработный"),
    EMPLOYEE("Наемный работник"),
    SELF_EMPLOYED("Самозанятый"),
    BUSINESS_OWNER("Владелец бизнеса");

    private final String employmentStatus;

    EmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

}
