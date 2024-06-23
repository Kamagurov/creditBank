package com.example.creditbank.deal.service.impl;

import com.example.creditbank.calculator.dto.EmploymentDto;
import com.example.creditbank.calculator.dto.LoanOfferDto;
import com.example.creditbank.calculator.dto.LoanStatementRequestDto;
import com.example.creditbank.calculator.dto.ScoringDataDto;
import com.example.creditbank.deal.enums.ApplicationStatus;
import com.example.creditbank.deal.feignclient.CalculatorClient;
import com.example.creditbank.deal.mapper.ClientMapper;
import com.example.creditbank.deal.mapper.CreditMapper;
import com.example.creditbank.deal.mapper.StatementMapper;
import com.example.creditbank.deal.mapper.StatusHistoriesMapper;
import com.example.creditbank.deal.model.dto.FinishRegistrationRequestDto;
import com.example.creditbank.deal.repositories.ClientRepository;
import com.example.creditbank.deal.repositories.CreditRepository;
import com.example.creditbank.deal.repositories.StatementRepository;
import com.example.creditbank.deal.service.DealService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class DealServiceImpl implements DealService {

    private final ClientMapper clientMapper;
    private final StatementMapper statementMapper;
    private final CreditMapper creditMapper;
    private final StatusHistoriesMapper statusHistoriesMapper;
    private final ClientRepository clientRepository;
    private final CalculatorClient calculatorClient;
    private final StatementRepository statementRepository;
    private final CreditRepository creditRepository;

    @Override
    public List<LoanOfferDto> appProcessing(LoanStatementRequestDto loanStatementRequestDto) {
        var client = clientRepository.save(clientMapper.createClientEntity(loanStatementRequestDto, null));
        var statement = statementRepository.save(statementMapper.createStatementEntity(loanStatementRequestDto, client));
        List<LoanOfferDto> offers = calculatorClient.createOffers(loanStatementRequestDto).getBody();

        for (LoanOfferDto offer : Objects.requireNonNull(offers)) {
            offer.setStatementId(statement.getStatementId());
        }

        offers.sort(Comparator.comparing(LoanOfferDto::getRate));

        return offers;
    }

    @Override
    public void selectOneOffer(LoanOfferDto loanOfferDto) {
        var statementId = loanOfferDto.getStatementId();
        var statement = statementRepository.findStatementEntitiesByStatementId(statementId).orElseThrow();

        statement.setStatus(ApplicationStatus.PREAPPROVAL);

        var statusHistory = statusHistoriesMapper.createStatusHistory(statement.getStatus().getApplicationStatus(), null);

        statement.setStatusHistories(statusHistoriesMapper.createStatusHistories(statusHistory));
        statement.setAppliedOffer(loanOfferDto);

        statementRepository.save(statement);
    }

    @Override
    public  void registerAndFullCreditCalculation(FinishRegistrationRequestDto finishRegistrationRequestDto, String statementId) {
        var statement = statementRepository.findStatementEntitiesByStatementId(UUID.fromString(statementId)).orElseThrow();
        var client = clientRepository.findClientEntityByClientId(statement.getClientId()).orElseThrow();
        var credit = creditRepository.findCreditEntityByCreditId(statement.getCreditId()).orElseThrow();

        var scoringDataDto = ScoringDataDto.builder()
                .amount(BigDecimal.valueOf(finishRegistrationRequestDto.getDependentAmount()))
                .term(statement.getAppliedOffer().getTerm())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .middleName(client.getMiddleName())
                .gender(client.getGender())
                .birthdate(LocalDate.from(client.getBirthDate()))
                .passportSeries(client.getPassportId().getSeries())
                .passportNumber(client.getPassportId().getNumber())
                .passportIssueBranch(client.getPassportId().getIssueBranch())
                .passportIssueDate(LocalDate.from(client.getPassportId().getIssueDate()))
                .maritalStatus(client.getMaritalStatus())
                .dependentAmount(client.getDependentAmount())
                .employmentDto(List.of(EmploymentDto.builder()
                        .employmentStatus(client.getEmploymentId().getStatus())
                        .employerINN(client.getEmploymentId().getEmploymentINN())
                        .salary(client.getEmploymentId().getSalary())
                        .position(client.getEmploymentId().getPosition())
                        .workExperienceTotal(client.getEmploymentId().getWorkExperienceTotal())
                        .workExperienceCurrent(client.getEmploymentId().getWorkExperienceCurrent())
                        .build()))
                .accountNumber(client.getAccountNumber())
                .isInsuranceEnabled(credit.getIsInsuranceEnabled())
                .isSalaryClient(credit.getIsSalaryClient())
                .build();

        ResponseEntity<com.example.creditbank.calculator.dto.CreditDto> creditDtoFromCalculatorService = calculatorClient.calculateCredit(scoringDataDto);

        creditRepository.save(creditMapper.createCreditEntity(Objects.requireNonNull(creditDtoFromCalculatorService.getBody())));
    }
}
