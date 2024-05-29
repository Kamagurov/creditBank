package com.example.creditbank.calculator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
public class LoanOfferDto {

    /**
     * Идентификатор (номер заявления?)
     */
    private UUID statementId;

    /**
     * Запрашиваемая сумма
     */
    private BigDecimal requestedAmount;

    /**
     * Сумма кредита
     */
    private BigDecimal totalAmount;

    /**
     * Срок выплаты кредита
     */
    private Integer term;

    /**
     * Кол-во месяцев выплаты кредита
     */
    private BigDecimal monthlyPayment;

    /**
     * Процентная ставка
     */
    private BigDecimal rate;

    /**
     * Наличие страховки (есть/нет)
     */
    private Boolean isInsuranceEnabled;

    /**
     * Зарплатный клиент (да/нет)
     */
    private Boolean isSalaryClient;
}
