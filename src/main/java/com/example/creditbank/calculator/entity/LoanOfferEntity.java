package com.example.creditbank.calculator.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
//@Entity
//@Table(name = "loan_offer")
@RequiredArgsConstructor
public class LoanOfferEntity {

    /**
     * Идентификатор (номер заявления?)
     */
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "statement_id", unique = true)
    private UUID statementId;

    /**
     * Запрашиваемая сумма
     */
//    @Column(name = "requested_amount", nullable = false)
    private BigDecimal requestedAmount;

    /**
     * Сумма выплаты кредита
     */
//    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    /**
     * Срок выплаты кредита
     */
//    @Column(name = "term")
    private Integer term;

    /**
     * Кол-во месяцев выплаты кредита
     */
//    @Column(name = "monthly_payment")
    private BigDecimal monthlyPayment;

    /**
     * Процентная ставка
     */
//    @Column(name = "rate")
    private BigDecimal rate;

    /**
     * Наличие страховки (есть/нет)
     */
//    @Column(name = "is_insurance_enabled")
    private Boolean isInsuranceEnabled;

    /**
     * Зарплатный клиент (да/нет)
     */
//    @Column(name = "is_salary_client")
    private Boolean isSalaryClient;
}
