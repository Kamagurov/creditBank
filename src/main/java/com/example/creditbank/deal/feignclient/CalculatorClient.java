package com.example.creditbank.deal.feignclient;

import com.example.creditbank.calculator.dto.CreditDto;
import com.example.creditbank.calculator.dto.LoanOfferDto;
import com.example.creditbank.calculator.dto.LoanStatementRequestDto;
import com.example.creditbank.calculator.dto.ScoringDataDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "creditBankCalculator", url = "http://localhost:8080", path = "/calculator")
public interface CalculatorClient {

    @PostMapping(path = "/offers",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<LoanOfferDto>> createOffers(@RequestBody LoanStatementRequestDto loanStatementRequestDto);

    @PostMapping(path = "/calc",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CreditDto> calculateCredit(@RequestBody ScoringDataDto scoringDataDto);
}