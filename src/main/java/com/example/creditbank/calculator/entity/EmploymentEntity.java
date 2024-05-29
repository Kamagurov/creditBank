package com.example.creditbank.calculator.entity;

import com.example.creditbank.calculator.enums.EmploymentPosition;
import com.example.creditbank.calculator.enums.EmploymentStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
//@Entity
//@Table(name = "employments")
@RequiredArgsConstructor
public class EmploymentEntity {

    /**
     * Идентификатор
     */
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    /**
     * Статус занятости
     */
//    @Column(name = "employment_status")
    private EmploymentStatus employmentStatus;

    /**
     * ИНН работодателя
     */
//    @Column(name = "employer_INN")
    private String employerINN;

    /**
     * Зарплата
     */
//    @Column(name = "salary")
    private BigDecimal salary;

    /**
     * Позиция
     */
//    @Column(name = "position")
    private EmploymentPosition position;

    /**
     * Общий стаж работы
     */
//    @Column(name = "work_experience_total")
    private Integer workExperienceTotal;

    /**
     * Стаж работы на текущем месте работы
     */
//    @Column(name = "work_experience_current")
    private Integer workExperienceCurrent;

}
