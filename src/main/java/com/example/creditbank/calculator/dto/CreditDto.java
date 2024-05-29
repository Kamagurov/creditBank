package com.example.creditbank.calculator.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class CreditDto {

    /**
     * Сумма выплаты кредита
     */
    private BigDecimal amount;

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
     *  Полная стоимость кредита
     */
    private BigDecimal psk;

    /**
     *  Наличие страховки
     */
    private Boolean isInsuranceEnabled;

    /**
     *  Наличие зарплаты
     */
    private Boolean isSalaryClient;

    /**
     *  График оплаты
     */
//    @ManyToOne(fetch = FetchType.LAZY)
    private List<PaymentScheduleElementDto> paymentSchedule;
}
