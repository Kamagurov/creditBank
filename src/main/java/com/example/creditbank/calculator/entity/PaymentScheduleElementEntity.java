package com.example.creditbank.calculator.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class PaymentScheduleElementEntity {

    private Integer number;

    private LocalDate date;

    private BigDecimal totalPayment;

    private BigDecimal interestPayment;

    private BigDecimal debtPayment;

    private BigDecimal remainingDebt;

}
