package com.example.creditbank.deal.model.dto;

import com.example.creditbank.deal.enums.CreditStatus;
import com.example.creditbank.deal.model.PaymentSchedule;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CreditDto {

    private BigDecimal amount;

    private Integer term;

    private BigDecimal monthlyPayment;

    private BigDecimal rate;

    private BigDecimal psk;

    private Boolean isInsuranceEnabled;

    private Boolean isSalaryClient;

    private PaymentSchedule paymentSchedule;

    private CreditStatus creditStatus;
}
