package com.example.creditbank.calculator.entity;

import com.example.creditbank.calculator.enums.EmploymentPosition;
import com.example.creditbank.calculator.enums.EmploymentStatus;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
@Data
@RequiredArgsConstructor
public class EmploymentEntity {

    private EmploymentStatus employmentStatus;

    private String employerINN;

    private BigDecimal salary;

    private EmploymentPosition position;

    private Integer workExperienceTotal;

    private Integer workExperienceCurrent;

}
