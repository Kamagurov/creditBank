package com.example.creditbank.calculator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import nonapi.io.github.classgraph.json.Id;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
public class LoanOfferDto {

    private UUID statementId;

    private BigDecimal requestedAmount;

    private BigDecimal totalAmount;

    private Integer term;

    private BigDecimal monthlyPayment;

    private BigDecimal rate;

    private Boolean isInsuranceEnabled;

    private Boolean isSalaryClient;
}
