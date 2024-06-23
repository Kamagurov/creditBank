package com.example.creditbank.deal.model.dto;

import com.example.creditbank.calculator.dto.LoanOfferDto;
import com.example.creditbank.deal.enums.ApplicationStatus;
import com.example.creditbank.deal.model.StatusHistory;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class StatementDto {

    private UUID creditId;

    private UUID clientId;

    private ApplicationStatus status;

    private LocalDate creationDate;

    private LoanOfferDto appliedOffer;

    private LocalDate signDate;

    private BigDecimal sesCode;

    private List<StatusHistory> statusHistories;
}
