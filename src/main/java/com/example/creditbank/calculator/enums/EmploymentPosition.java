package com.example.creditbank.calculator.enums;

import lombok.Getter;

/**
 * Позиция на работе
 */

@Getter
public enum EmploymentPosition {

    MID_MANAGER("Менеджер среднего звена"),
    TOP_MANAGER("Топ-менеджер"),
    WORKER("Рабочий");

    private final String position;

    EmploymentPosition(String position) {
        this.position = position;
    }
}
