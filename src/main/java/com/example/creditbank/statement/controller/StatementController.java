package com.example.creditbank.statement.controller;

import com.example.creditbank.calculator.dto.LoanOfferDto;
import com.example.creditbank.calculator.dto.LoanStatementRequestDto;
import com.example.creditbank.statement.service.StatementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/statement")
@Slf4j
@RequiredArgsConstructor
public class StatementController {

    private final StatementService statementService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LoanOfferDto> prescoringAndCalculate(@RequestBody LoanStatementRequestDto loanStatementRequestDto) {
        return statementService.prescoringAndCalculate(loanStatementRequestDto);
    }

    @PostMapping(path = "/offer",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void selectOneOffer(@RequestBody LoanOfferDto loanOfferDto) {
        statementService.selectOneOffer(loanOfferDto);
    }
}
