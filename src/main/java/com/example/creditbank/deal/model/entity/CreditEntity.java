package com.example.creditbank.deal.model.entity;

import com.example.creditbank.deal.enums.CreditStatus;
import com.example.creditbank.deal.model.PaymentSchedule;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "credit")
public class CreditEntity {

    @Id
    @Column(name = "credit_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID creditId;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "term")
    private Integer term;

    @Column(name = "monthly_payment")
    private BigDecimal monthlyPayment;

    @Column(name = "rate")
    private BigDecimal rate;

    @Column(name = "psk")
    private BigDecimal psk;

    @Column(name = "insurance_enabled")
    private Boolean isInsuranceEnabled;

    @Column(name = "salary_client")
    private Boolean isSalaryClient;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "payment_schedule", columnDefinition = "jsonb")
    private List<PaymentSchedule> paymentSchedule;

    @Column(name = "credit_status")
    @Enumerated(EnumType.STRING)
    private CreditStatus creditStatus;
}
