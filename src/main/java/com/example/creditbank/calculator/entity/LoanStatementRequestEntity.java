package com.example.creditbank.calculator.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
//@Entity
//@Table(name = "loan_statement_request")
@RequiredArgsConstructor
public class LoanStatementRequestEntity {

    /**
     * Сумма выплаты кредита
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
