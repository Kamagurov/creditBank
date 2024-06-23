package com.example.creditbank.deal.service;

import com.example.creditbank.calculator.dto.LoanOfferDto;
import com.example.creditbank.calculator.dto.LoanStatementRequestDto;
import com.example.creditbank.deal.model.dto.FinishRegistrationRequestDto;

import java.util.List;

public interface DealService {

    List<LoanOfferDto> appProcessing(LoanStatementRequestDto loanStatementRequestDto);

    void selectOneOffer(LoanOfferDto loanOfferDto);

    void registerAndFullCreditCalculation(FinishRegistrationRequestDto finishRegistrationRequestDto, String statementId);
}
