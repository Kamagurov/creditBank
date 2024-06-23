package com.example.creditbank.deal.model.dto;

import com.example.creditbank.calculator.dto.EmploymentDto;
import com.example.creditbank.calculator.enums.Gender;
import com.example.creditbank.calculator.enums.MaritalStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FinishRegistrationRequestDto {

    private Gender gender;

    private MaritalStatus maritalStatus;

    private Integer dependentAmount;

    private LocalDate passportIssueDate;

    private EmploymentDto employment;

    private String accountNumber;
}
