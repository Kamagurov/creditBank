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

    private BigDecimal amount;

    private Integer term;

    private String firstName;

    private String lastName;

    private String middleName;

    private String email;

    private LocalDate birthdate;

    private String passportSeries;

    private String passportNumber;
}
