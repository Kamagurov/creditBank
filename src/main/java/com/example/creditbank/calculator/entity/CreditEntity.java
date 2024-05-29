package com.example.creditbank.calculator.entity;

import com.example.creditbank.calculator.dto.PaymentScheduleElementDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
//@Entity
//@Table(name = "credits")
@RequiredArgsConstructor
public class CreditEntity {


    /**
     * Сумма выплаты кредита
     */
//    @Column(name = "amount")
    private BigDecimal amount;

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
     *  Полная стоимость кредита
     */
//    @Column(name = "psk")
    private BigDecimal psk;

    /**
     *  Наличие страховки
     */
//    @Column(name = "is_insurance_enabled")
    private Boolean isInsuranceEnabled;

    /**
     *  Наличие зарплаты
     */
//    @Column(name = "is_salary_client", nullable = false)
    private Boolean isSalaryClient;

    /**
     *  График оплаты
     */
//    @ManyToOne(fetch = FetchType.LAZY)
    private List<PaymentScheduleElementDto> paymentSchedule;
}
