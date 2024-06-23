package com.example.creditbank.calculator.service.impl;

import com.example.creditbank.calculator.dto.EmploymentDto;
import com.example.creditbank.calculator.dto.LoanStatementRequestDto;
import com.example.creditbank.calculator.dto.ScoringDataDto;
import com.example.creditbank.calculator.enums.EmploymentPosition;
import com.example.creditbank.calculator.enums.EmploymentStatus;
import com.example.creditbank.calculator.enums.Gender;
import com.example.creditbank.calculator.enums.MaritalStatus;
import com.example.creditbank.calculator.service.CalculationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CalculationServiceImplTest {

    @Autowired
    private CalculationService calculationService;

    @Test
    @DisplayName("Создание кредитного предложения")
    void calculateCreditTest() {
        var scoringDataDto = ScoringDataDto.builder()
                .amount(BigDecimal.valueOf(500))
                .firstName("MAKAR")
                .lastName("KAMAR")
                .middleName("MK")
                .term(10)
                .gender(Gender.MALE)
                .birthdate(LocalDateTime.of(1990, 10, 10, 10, 10))
                .passportSeries("896532")
                .passportNumber("458798")
                .passportIssueDate(LocalDateTime.of(2010, 2, 2, 2, 2))
                .passportIssueBranch("ОУФМС")
                .maritalStatus(MaritalStatus.SINGLE)
                .dependentAmount(1000)
                .employmentDto(List.of(EmploymentDto.builder()
                        .employerINN("4578653289")
                        .employmentStatus(EmploymentStatus.SELF_EMPLOYED)
                        .position(EmploymentPosition.WORKER)
                        .salary(BigDecimal.valueOf(547898))
                        .workExperienceCurrent(10)
                        .workExperienceTotal(25)
                        .build()))
                .accountNumber("kjdgfjk")
                .isInsuranceEnabled(true)
                .isSalaryClient(true)
                .build();

        var creditDto = calculationService.calculateCredit(scoringDataDto);

        assertEquals(scoringDataDto.getAmount(), creditDto.getAmount());
        assertEquals(scoringDataDto.getTerm(), creditDto.getTerm());
        assertEquals(scoringDataDto.getIsInsuranceEnabled(), creditDto.getIsInsuranceEnabled());
        assertEquals(scoringDataDto.getIsSalaryClient(), creditDto.getIsSalaryClient());
    }

    @Test
    @DisplayName("Создание кредитного предложения")
    void createOffersTest() {
        var loanStatementRequestDto = LoanStatementRequestDto.builder()
                .amount(BigDecimal.valueOf(458700))
                .term(10)
                .firstName("Lana")
                .lastName("Nala")
                .middleName("LN")
                .birthdate(LocalDateTime.of(2000, 5, 5, 5, 5))
                .email("sjkdfhsjd@ldkjfh.ru")
                .passportSeries("457898")
                .passportNumber("452165")
                .build();

        var offers = calculationService.createOffers(loanStatementRequestDto);

        assertEquals(loanStatementRequestDto.getAmount(), offers.get(0).getRequestedAmount());
        assertEquals(loanStatementRequestDto.getTerm(), offers.get(0).getTerm());
    }
}