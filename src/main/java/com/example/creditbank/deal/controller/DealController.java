package com.example.creditbank.deal.controller;

import com.example.creditbank.calculator.dto.LoanOfferDto;
import com.example.creditbank.calculator.dto.LoanStatementRequestDto;
import com.example.creditbank.deal.model.dto.FinishRegistrationRequestDto;
import com.example.creditbank.deal.service.DealService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/deal")
public class DealController {

    private final DealService dealService;

    @PostMapping(path = "/statement",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LoanOfferDto>> createOffers(@RequestBody LoanStatementRequestDto loanStatementRequestDto) {
        return ResponseEntity.ok(dealService.appProcessing(loanStatementRequestDto));
    }

    @PostMapping(path = "/offer/select",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void selectOneOffer(@RequestBody LoanOfferDto loanOfferDto) {

    }

    @PostMapping(path = "/calculate/{statementId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void fullCreditCalculation(FinishRegistrationRequestDto finishRegistrationRequestDto, @PathVariable String statementId) {

    }
}
