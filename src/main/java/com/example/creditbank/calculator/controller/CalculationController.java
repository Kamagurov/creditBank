package com.example.creditbank.calculator.controller;

import com.example.creditbank.calculator.dto.CreditDto;
import com.example.creditbank.calculator.dto.LoanOfferDto;
import com.example.creditbank.calculator.dto.LoanStatementRequestDto;
import com.example.creditbank.calculator.dto.ScoringDataDto;
import com.example.creditbank.calculator.service.CalculationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(name = "/calculator")
@RequiredArgsConstructor
public class CalculationController {

    private CalculationService calculationService;

    @PostMapping(value = "/offers")
    public List<LoanOfferDto> getOffers(@RequestBody LoanStatementRequestDto loanStatementRequestDto) {
        return new ResponseEntity<>(calculationService.getOffers(loanStatementRequestDto), HttpStatus.ACCEPTED).getBody();
    }

    @PostMapping(value = "/calc")
    public ResponseEntity<CreditDto> calculateCredit(@RequestBody ScoringDataDto scoringDataDto) {
        return new ResponseEntity<>(calculationService.calculateCredit(scoringDataDto), HttpStatus.ACCEPTED);
    }
}
