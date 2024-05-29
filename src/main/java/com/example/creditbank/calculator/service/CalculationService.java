package com.example.creditbank.calculator.service;

import com.example.creditbank.calculator.dto.CreditDto;
import com.example.creditbank.calculator.dto.LoanOfferDto;
import com.example.creditbank.calculator.dto.LoanStatementRequestDto;
import com.example.creditbank.calculator.dto.ScoringDataDto;

import java.util.List;

public interface CalculationService {

    public List<LoanOfferDto> getOffers(LoanStatementRequestDto loanStatementRequestDto);

    public CreditDto calculateCredit(ScoringDataDto scoringDataDto);
}
