package com.example.creditbank.deal.model.entity;

import com.example.creditbank.calculator.dto.LoanOfferDto;
import com.example.creditbank.deal.enums.ApplicationStatus;
import com.example.creditbank.deal.model.StatusHistory;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "statement")
public class StatementEntity {

    @Id
    @Column(name = "statement_id")
    @GeneratedValue
    @UuidGenerator
    private UUID statementId;

    @JoinColumn(name = "credit_id")
    private UUID creditId;

    @JoinColumn(name = "client_id")
    private UUID clientId;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "applied_offer", columnDefinition = "jsonb")
    private LoanOfferDto appliedOffer;

    @Column(name = "sign_date")
    private LocalDateTime signDate;

    @Column(name = "ses_code")
    private BigDecimal sesCode;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "status_history", columnDefinition = "jsonb")
    private List<StatusHistory> statusHistories;
}
