package com.example.creditbank.calculator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
public class PaymentScheduleElementDto {

    /*
     * Номер чего?
     */
    private Integer number;

    /*
     * Дата ежемесячного платежа
     */
    private LocalDate date;

    /*
     * Общий долг
     */
    private BigDecimal totalPayment;

    /*
     * Сумма всех процентов
     */
    private BigDecimal interestPayment;

    /*
     * Сумма ежемесячного платежа
     */
    private BigDecimal debtPayment;

    /*
     * Оставшийся долг
     */
    private BigDecimal remainingDebt;
}
