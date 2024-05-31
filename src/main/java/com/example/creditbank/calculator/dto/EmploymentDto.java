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

    private EmploymentStatus employmentStatus;

    private String employerINN;

    private BigDecimal salary;

    private EmploymentPosition position;

    private Integer workExperienceTotal;

    private Integer workExperienceCurrent;
}
