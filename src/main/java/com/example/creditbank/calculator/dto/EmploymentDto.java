package com.example.creditbank.calculator.dto;

import com.example.creditbank.calculator.enums.EmploymentPosition;
import com.example.creditbank.calculator.enums.EmploymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
public class EmploymentDto {

    /**
     * Идентификатор
     */
    private Long id;

    /**
     * Статус занятости
     */
    private EmploymentStatus employmentStatus;

    /**
     * ИНН работодателя
     */
    private String employerINN;

    /**
     * Зарплата
     */
    private BigDecimal salary;

    /**
     * Позиция
     */
    private EmploymentPosition position;

    /**
     * Общий стаж работы
     */
    private Integer workExperienceTotal;

    /**
     * Стаж работы на текущем месте работы
     */
    private Integer workExperienceCurrent;
}
