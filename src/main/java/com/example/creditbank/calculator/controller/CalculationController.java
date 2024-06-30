package com.example.creditbank.calculator.controller;

import com.example.creditbank.calculator.dto.CreditDto;
import com.example.creditbank.calculator.dto.LoanOfferDto;
import com.example.creditbank.calculator.dto.LoanStatementRequestDto;
import com.example.creditbank.calculator.dto.ScoringDataDto;
import com.example.creditbank.calculator.service.CalculationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/calculator")
@RequiredArgsConstructor
public class CalculationController {

    private final CalculationService calculationService;

    @PostMapping(path = "/offers",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LoanOfferDto>> createOffers(@Valid @RequestBody LoanStatementRequestDto loanStatementRequestDto) {
        return ResponseEntity.ok(calculationService.createOffers(loanStatementRequestDto));
    }

    @PostMapping(path = "/calc",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreditDto> calculateCredit(@RequestBody ScoringDataDto scoringDataDto) {
        return ResponseEntity.ok(calculationService.calculateCredit(scoringDataDto));
    }
}
