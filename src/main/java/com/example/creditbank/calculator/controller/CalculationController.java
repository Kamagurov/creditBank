package com.example.creditbank.calculator.controller;

import com.example.creditbank.calculator.dto.CreditDto;
import com.example.creditbank.calculator.dto.LoanOfferDto;
import com.example.creditbank.calculator.dto.LoanStatementRequestDto;
import com.example.creditbank.calculator.dto.ScoringDataDto;
import com.example.creditbank.calculator.service.CalculationService;
import jdk.jfr.ContentType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(name = "/calculator")
@RequiredArgsConstructor
public class CalculationController {

    final private CalculationService calculationService;

    @PostMapping(value = "/offers",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LoanOfferDto> createOffers(@RequestBody LoanStatementRequestDto loanStatementRequestDto) {
        return ResponseEntity.ok(calculationService.createOffers(loanStatementRequestDto)).getBody();
    }

    @PostMapping(value = "/calc",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreditDto> calculateCredit(@RequestBody ScoringDataDto scoringDataDto) {
        return new ResponseEntity<>(calculationService.calculateCredit(scoringDataDto), HttpStatus.ACCEPTED);
    }
}
