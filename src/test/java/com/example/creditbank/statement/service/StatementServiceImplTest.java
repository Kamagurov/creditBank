package com.example.creditbank.statement.service;

import com.example.creditbank.calculator.dto.LoanOfferDto;
import com.example.creditbank.calculator.dto.LoanStatementRequestDto;
import com.example.creditbank.deal.feignclient.CalculatorClient;
import com.example.creditbank.statement.feignclient.DealClient;
import com.example.creditbank.statement.service.impl.StatementServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatementServiceImplTest {

    @Mock
    private DealClient dealClient;

    @Mock
    private CalculatorClient calculatorClient;

    @InjectMocks
    private StatementServiceImpl statementService;

    @Test
    @DisplayName("Прескоринг данных LoanStatementRequestDto")
    public void prescoringRequestDtoTest() {
        var loanStatementRequestDto = LoanStatementRequestDto.builder()
                .amount(BigDecimal.valueOf(50000))
                .term(20)
                .firstName("Петр")
                .lastName("Тополев")
                .middleName("Михайлович")
                .email("PetrT@mail.ru")
                .birthdate(LocalDate.of(2020, 10, 10))
                .passportNumber("5445")
                .passportSeries("656511")
                .build();

        var loanOfferDto = LoanOfferDto.builder()
                .requestedAmount(BigDecimal.valueOf(50000))
                .totalAmount(BigDecimal.valueOf(150000))
                .term(20)
                .monthlyPayment(BigDecimal.valueOf(7056))
                .rate(BigDecimal.valueOf(10))
                .isSalaryClient(true)
                .isInsuranceEnabled(true)
                .build();

        ResponseEntity<List<LoanOfferDto>> mockResponse = ResponseEntity.ok(List.of(loanOfferDto));
        when(dealClient.createOffers(loanStatementRequestDto)).thenReturn(mockResponse);

        var loanOfferDtoAfterCheck = statementService.prescoringAndCalculate(loanStatementRequestDto);
        var offer = loanOfferDtoAfterCheck.stream()
                .findFirst()
                .orElseThrow();

        assertEquals(loanStatementRequestDto.getAmount(), offer.getRequestedAmount());
        assertEquals(loanStatementRequestDto.getTerm(), offer.getTerm());
    }

    @Test
    void selectOneOfferTest() {
        var loanOfferDto = LoanOfferDto.builder()
                .requestedAmount(BigDecimal.valueOf(50000))
                .totalAmount(BigDecimal.valueOf(150000))
                .term(7)
                .monthlyPayment(BigDecimal.valueOf(7056))
                .rate(BigDecimal.valueOf(10))
                .isSalaryClient(true)
                .isInsuranceEnabled(true)
                .build();

        statementService.selectOneOffer(loanOfferDto);

        verify(dealClient).selectOneOffer(loanOfferDto);
    }

}