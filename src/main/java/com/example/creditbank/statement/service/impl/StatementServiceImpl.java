package com.example.creditbank.statement.service.impl;

import com.example.creditbank.calculator.dto.LoanOfferDto;
import com.example.creditbank.calculator.dto.LoanStatementRequestDto;
import com.example.creditbank.statement.feignclient.DealClient;
import com.example.creditbank.statement.service.StatementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StatementServiceImpl implements StatementService {

    private final DealClient dealClient;

    @Override
    public List<LoanOfferDto> prescoringAndCalculate(LoanStatementRequestDto loanStatementRequestDto) {
        return dealClient.createOffers(loanStatementRequestDto).getBody();
    }

    @Override
    public void selectOneOffer(LoanOfferDto loanOfferDto) {
        dealClient.selectOneOffer(loanOfferDto);
    }
}
