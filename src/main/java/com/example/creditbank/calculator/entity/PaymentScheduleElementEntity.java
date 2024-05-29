package com.example.creditbank.calculator.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
//@Entity
//@Table(name = "payment_shedule_element")
@RequiredArgsConstructor
public class PaymentScheduleElementEntity {

    /*
     * Номер чего?
     */
//    @Column(name = "number")
    private Integer number;

    /*
     * Дата ежемесячного платежа
     */
//    @Column(name = "date")
    private LocalDate date;

    /*
     * Общий долг
     */
//    @Column(name = "total_payment")
    private BigDecimal totalPayment;

    /*
     * Сумма всех процентов
     */
//    @Column(name = "interest_payment")
    private BigDecimal interestPayment;

    /*
     * Сумма ежемесячного платежа
     */
//    @Column(name = "debt_payment")
    private BigDecimal debtPayment;

    /*
     * Оставшийся долг
     */
//    @Column(name = "remaining_debt")
    private BigDecimal remainingDebt;

}
