package com.example.creditbank.deal.model;

import com.example.creditbank.calculator.enums.EmploymentPosition;
import com.example.creditbank.calculator.enums.EmploymentStatus;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
public class Employment implements Serializable {

    private UUID employment_uuid;

    private EmploymentStatus status;

    private String employmentINN;

    private BigDecimal salary;

    private EmploymentPosition position;

    private Integer workExperienceTotal;

    private Integer workExperienceCurrent;
}
