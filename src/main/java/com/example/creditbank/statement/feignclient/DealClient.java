package com.example.creditbank.statement.feignclient;

import com.example.creditbank.calculator.dto.LoanOfferDto;
import com.example.creditbank.calculator.dto.LoanStatementRequestDto;
import com.example.creditbank.deal.model.dto.FinishRegistrationRequestDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "creditBank-Deal", url = "http://localhost:8080", path = "/deal")
public interface DealClient {

    @PostMapping(path = "/statement",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<LoanOfferDto>> createOffers(@RequestBody LoanStatementRequestDto loanStatementRequestDto);

    @PostMapping(path = "/offer/select",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    void selectOneOffer(@RequestBody LoanOfferDto loanOfferDto);

    @PostMapping(path = "/calculate/{statementId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    void fullCreditCalculation(FinishRegistrationRequestDto finishRegistrationRequestDto, @PathVariable String statementId);
}
