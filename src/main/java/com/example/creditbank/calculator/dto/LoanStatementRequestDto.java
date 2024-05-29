package com.example.creditbank.calculator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
public class LoanStatementRequestDto {

    /**
     * Сумма кредита
     */
    private BigDecimal amount;

    /**
     * Срок выплаты кредита
     */
    private Integer term;

    /**
     * Имя
     */
    private String firstName;

    /**
     * Фамилия
     */
    private String lastName;

    /**
     * Отчество
     */
    private String middleName;

    /**
     * Почта
     */
    private String email;

    /**
     * Дата рождения
     */
    private LocalDate birthdate;

    /**
     * Серия паспорта
     */
    private String passportSeries;

    /**
     * Номер паспорта
     */
    private String passportNumber;
}
