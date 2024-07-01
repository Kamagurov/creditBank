package com.example.creditbank.statement.service;

import com.example.creditbank.calculator.dto.LoanOfferDto;
import com.example.creditbank.calculator.dto.LoanStatementRequestDto;

import java.util.List;

public interface StatementService {

    List<LoanOfferDto> prescoringAndCalculate(LoanStatementRequestDto loanStatementRequestDto);

    void selectOneOffer(LoanOfferDto loanOfferDto);
}
