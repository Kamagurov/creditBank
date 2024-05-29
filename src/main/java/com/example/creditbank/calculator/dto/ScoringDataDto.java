package com.example.creditbank.calculator.dto;

import com.example.creditbank.calculator.enums.Gender;
import com.example.creditbank.calculator.enums.MaritalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
public class ScoringDataDto {

    /**
     * Сумма кредита
     */
    private BigDecimal amount;

    /**
     * Срок выплаты кредита
     */
    private Integer term;

    /**
     * Имя сотрудника
     */
    private String firstName;

    /**
     * Фамилия сотрудника
     */
    private String lastName;

    /**
     * Отчество сотрудника
     */
    private String middleName;

    /**
     * Пол
     */
    private Gender gender;

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

    /**
     * Дата выдачи паспорта
     */
    private LocalDate passportIssueDate;

    /**
     * Кем выдан паспорт
     */
    private String passportIssueBranch;

    /**
     * Семейное положение
     */
    private MaritalStatus maritalStatus;

    /**
     *  Запрашиваемая сумма кредита
     */
    private Integer dependentAmount;

    /**
     * Информация о работе
     */
    private EmploymentDto employmentDto;

    /**
     * Номер счета
     */
    private String accountNumber;

    /**
     * Статус страховки
     */
    private Boolean isInsuranceEnabled;

    /**
     * Статус получения зарплаты
     */
    private Boolean isSalaryClient;
}
