package com.example.creditbank.calculator.entity;

import com.example.creditbank.calculator.dto.EmploymentDto;
import com.example.creditbank.calculator.enums.Gender;
import com.example.creditbank.calculator.enums.MaritalStatus;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
//@Entity
//@Table(name = "scoring_data")
@RequiredArgsConstructor
public class ScoringDataEntity {

    /**
     * Сумма кредита
     */
//    @Column(name = "amount")
    private BigDecimal amount;

    /**
     * Срок выплаты кредита
     */
//    @Column(name = "term")
    private Integer term;

    /**
     * Имя сотрудника
     */
//    @Column(name = "first_name")
    private String firstName;

    /**
     * Фамилия сотрудника
     */
//    @Column(name = "last_name")
    private String lastName;

    /**
     * Отчество сотрудника
     */
//    @Column(name = "middle_name")
    private String middleName;

    /**
     * Пол
     */
//    @Column(name = "gender")
    private Gender gender;

    /**
     * Дата рождения
     */
//    @Column(name = "birthdate")
    private LocalDate birthdate;

    /**
     * Серия паспорта
     */
//    @Column(name = "passport_series")
    private String passportSeries;

    /**
     * Номер паспорта
     */
//    @Column(name = "passport_number")
    private String passportNumber;

    /**
     * Дата выдачи паспорта
     */
//    @Column(name = "passport_issue_date")
    private LocalDate passportIssueDate;

    /**
     * Кем выдан паспорт
     */
//    @Column(name = "passport_issue_branch")
    private String passportIssueBranch;

    /**
     * Семейное положение
     */
//    @Column(name = "marital_status")
    private MaritalStatus maritalStatus;

    /**
     *  Запрашиваемая сумма кредита
     */
//    @Column(name = "dependent_amount")
    private Integer dependentAmount;

    /**
     * Информация о работе
     */
//    @Column(name = "employment_id")
    private EmploymentDto employmentDto;

    /**
     * Номер счета
     */
//    @Column(name = "account_number")
    private String accountNumber;

    /**
     * Статус страховки
     */
//    @Column(name = "is_insurance_enabled")
    private Boolean isInsuranceEnabled;

    /**
     * Статус получения зарплаты
     */
//    @Column(name = "is_salary_client")
    private Boolean isSalaryClient;

}
