//package com.example.creditbank.deal.service.Impl;
//
//import com.example.creditbank.calculator.dto.EmploymentDto;
//import com.example.creditbank.calculator.dto.LoanOfferDto;
//import com.example.creditbank.calculator.dto.LoanStatementRequestDto;
//import com.example.creditbank.calculator.dto.ScoringDataDto;
//import com.example.creditbank.calculator.enums.EmploymentPosition;
//import com.example.creditbank.calculator.enums.EmploymentStatus;
//import com.example.creditbank.calculator.enums.Gender;
//import com.example.creditbank.calculator.enums.MaritalStatus;
//import com.example.creditbank.deal.enums.CreditStatus;
//import com.example.creditbank.deal.feignclient.CalculatorClient;
//import com.example.creditbank.deal.mapper.ClientMapper;
//import com.example.creditbank.deal.mapper.CreditMapper;
//import com.example.creditbank.deal.mapper.StatementMapper;
//import com.example.creditbank.deal.model.dto.ClientDto;
//import com.example.creditbank.deal.model.dto.CreditDto;
//import com.example.creditbank.deal.model.dto.FinishRegistrationRequestDto;
//import com.example.creditbank.deal.model.entity.CreditEntity;
//import com.example.creditbank.deal.model.entity.StatementEntity;
//import com.example.creditbank.deal.repositories.ClientRepository;
//import com.example.creditbank.deal.repositories.CreditRepository;
//import com.example.creditbank.deal.repositories.StatementRepository;
//import com.example.creditbank.deal.service.impl.DealServiceImpl;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.ResponseEntity;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class DealServiceImplTest {
//
//    @Mock
//    private ClientMapper clientMapper;
//    @Mock
//    private CreditMapper creditMapper;
//    @Mock
//    private StatementMapper statementMapper;
//    @Mock
//    private ClientRepository clientRepository;
//    @Mock
//    private CalculatorClient calculatorClient;
//    @Mock
//    private StatementRepository statementRepository;
//    @Mock
//    private CreditRepository creditRepository;
//
//    @InjectMocks
//    private DealServiceImpl dealService;
//
//    @Test
//    @DisplayName("Расчет возможных условий кредита")
//    void appProcessingTest() {
//        LoanStatementRequestDto loanStatementRequestDto = getLoanStatementRequestDto();
//        var clientDto = ClientDto.builder()
//                .firstName(loanStatementRequestDto.getFirstName())
//                .lastName(loanStatementRequestDto.getLastName())
//                .middleName(loanStatementRequestDto.getMiddleName())
//                .birthDate(loanStatementRequestDto.getBirthdate())
//                .email(loanStatementRequestDto.getEmail())
//                .gender(null)
//                .maritalStatus(null)
//                .dependentAmount(loanStatementRequestDto.getAmount().intValue())
//                .employment(null)
//                .passport(null)
//                .accountNumber(null)
//                .build();
//    }
//    @Test
//    void appProcessingTest1() {
//        LoanStatementRequestDto loanStatementRequestDto = getLoanStatementRequestDto();
//
//        List<LoanOfferDto> loanOfferDtos = calculatorClient.createOffers(loanStatementRequestDto).getBody();
//
//        when(calculatorClient.createOffers(loanStatementRequestDto)).thenReturn(ResponseEntity.ok(loanOfferDtos));
//        when(clientRepository.save(any())).thenReturn(null);
//        when(statementRepository.save(any())).thenReturn(null);
//
//        List<LoanOfferDto> result = dealService.appProcessing(loanStatementRequestDto);
//
//        assertNotNull(result);
//        assertEquals(2, result.size());
//        verify(calculatorClient, times(1)).createOffers(loanStatementRequestDto);
//    }
//
//    @Test
//    void selectOneOfferTest() {
//        UUID statementId = UUID.randomUUID();
//
//        LoanOfferDto loanOfferDto = LoanOfferDto.builder()
//                .requestedAmount(BigDecimal.valueOf(125))
//                .totalAmount(BigDecimal.valueOf(15))
//                .term(10)
//                .monthlyPayment(BigDecimal.valueOf(99))
//                .rate(BigDecimal.valueOf(90))
//                .isSalaryClient(true)
//                .isInsuranceEnabled(true)
//                .build();
//
//        var client = clientMapper.createClient(getLoanStatementRequestDto(), null);
//
//        when(statementRepository.findStatementEntitiesByStatementId(statementId))
//                .thenReturn(Optional.of(statementMapper.createStatementEntity(getLoanStatementRequestDto(), client)));
//
//        dealService.selectOneOffer(loanOfferDto);
//
//        verify(statementRepository, times(1)).findStatementEntitiesByStatementId(statementId);
//        verify(statementRepository, times(1)).save(any(StatementEntity.class));
//    }
//
//    @Test
//    void registerAndFullCreditCalculationTest() {
//        FinishRegistrationRequestDto registrationDto = new FinishRegistrationRequestDto();
//        String statementId = "some-id";
//
//        var client = clientMapper.createClient(getLoanStatementRequestDto(), null);
//
//        var scoringDataDto = ScoringDataDto.builder()
//                .amount(BigDecimal.valueOf(500000))
//                .term(20)
//                .firstName("Name")
//                .lastName("LastName")
//                .middleName("Middle")
//                .gender(Gender.MALE)
//                .birthdate(LocalDate.of(2000, 10, 10))
//                .passportSeries("458745")
//                .passportNumber("548745")
//                .passportIssueBranch("true")
//                .passportIssueDate(LocalDate.of(2018, 10, 15))
//                .maritalStatus(MaritalStatus.SINGLE)
//                .dependentAmount(500000)
//                .employmentDto(List.of(EmploymentDto.builder()
//                        .employmentStatus(EmploymentStatus.SELF_EMPLOYED)
//                        .employerINN("54784587458745")
//                        .salary(BigDecimal.valueOf(150000))
//                        .position(EmploymentPosition.WORKER)
//                        .workExperienceTotal(25)
//                        .workExperienceCurrent(25)
//                        .build()))
//                .accountNumber("1245ds98")
//                .isInsuranceEnabled(true)
//                .isSalaryClient(true)
//                .build();
//
//        var creditDto = calculatorClient.calculateCredit(scoringDataDto).getBody();
//
//        when(statementRepository.findStatementEntitiesByStatementId(UUID.fromString(statementId)))
//                .thenReturn(Optional.of(statementMapper.createStatementEntity(getLoanStatementRequestDto(), client)));
//        when(clientRepository.findClientEntityByClientId(any()))
//                .thenReturn(Optional.of(clientMapper.createClient(getLoanStatementRequestDto(), null)));
//        when(creditRepository.findCreditEntityByCreditId(any()))
//                .thenReturn(Optional.of(creditMapper.createCreditEntity(creditMapper.toEntity(creditDto), null)));
//
//        dealService.registerAndFullCreditCalculation(registrationDto, statementId);
//
//        verify(creditRepository, times(1)).save(any(CreditEntity.class));
//    }
//
//    private LoanStatementRequestDto getLoanStatementRequestDto() {
//        return LoanStatementRequestDto.builder()
//                .amount(BigDecimal.valueOf(19800))
//                .term(8)
//                .firstName("Машина")
//                .lastName("Автомобилев")
//                .middleName("Зилович")
//                .birthdate(LocalDate.of(1988, 10, 22))
//                .email("test@test.com")
//                .passportSeries("4587")
//                .passportNumber("547854")
//                .build();
//    }
//}
