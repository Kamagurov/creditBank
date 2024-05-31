package com.example.creditbank.calculator.service.impl;

import com.example.creditbank.calculator.dto.*;
import com.example.creditbank.calculator.enums.Gender;
import com.example.creditbank.calculator.exception.ForbiddenException;
import com.example.creditbank.calculator.service.CalculationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class CalculationServiceImpl implements CalculationService {

    private static final BigDecimal INSURANCE_ADDITIONAL_AMOUNT = BigDecimal.valueOf(100000);

    private static final String CREDITOR_STATUS = "Статус заёмщика %s = %s не удовлетворяет условиям.";
    private static final String SALARY_AMOUNT = "Зарплата %s = %s не удовлетворяет условиям.";
    private static final String AGE_VERIFICATION = "Возраст %s = %s не удовлетворяет условиям.";
    private static final String WORK_EXPERIENCE = "Стаж работы не удовлетворяет условиям.";

    private final BigDecimal BASE_RATE = BigDecimal.valueOf(18L);

    @Override
    public List<LoanOfferDto> createOffers(LoanStatementRequestDto loanStatementRequestDto) {
        List<LoanOfferDto> offers = new ArrayList<>();
        var requestAmount = loanStatementRequestDto.getAmount();
        var term = loanStatementRequestDto.getTerm();

        for (List<Boolean> combination : getAnotherOnaCombination()) {
            boolean insuranceEnabled = combination.get(0);
            boolean isSalaryClient = combination.get(1);

            LoanOfferDto offer = createSingleOffer(requestAmount, term, insuranceEnabled, isSalaryClient);
            offers.add(offer);
        }

        offers.sort(Comparator.comparing(LoanOfferDto::getRate));

        return offers;
    }

    private List<List<Boolean>> getAnotherOnaCombination() {
        return Arrays.asList(
                Arrays.asList(false, false),
                Arrays.asList(false, true),
                Arrays.asList(true, false),
                Arrays.asList(true, true)
        );
    }

    private LoanOfferDto createSingleOffer(BigDecimal requestedAmount, int term, boolean insuranceEnabled, boolean isSalaryClient) {
        var rate = calculateRate(insuranceEnabled, isSalaryClient);
        var totalAmount = calculateTotalAmount(requestedAmount, insuranceEnabled);
        var monthlyPayment = calculateMonthlyPayment(totalAmount, term, rate);

        return LoanOfferDto.builder()
                .requestedAmount(requestedAmount)
                .totalAmount(totalAmount)
                .term(term)
                .monthlyPayment(monthlyPayment)
                .rate(rate)
                .isInsuranceEnabled(insuranceEnabled)
                .isSalaryClient(isSalaryClient)
                .build();
    }

    private BigDecimal calculateRate(boolean isInsuranceEnabled, boolean isSalaryClient) {
        var baseRate = BASE_RATE;
        if (isInsuranceEnabled) {
            baseRate = baseRate.subtract(BigDecimal.valueOf(5));
        }
        if (isSalaryClient) {
            baseRate = baseRate.subtract(BigDecimal.valueOf(3));
        }
        return baseRate;
    }

    private BigDecimal calculateTotalAmount(BigDecimal requestAmount, boolean isInsuranceEnabled) {
        var totalAmount = requestAmount;
        if (isInsuranceEnabled) {
            totalAmount = totalAmount.add(INSURANCE_ADDITIONAL_AMOUNT);
        }
        return totalAmount;
    }

    @Override
    public CreditDto calculateCredit(ScoringDataDto scoringDataDto) {
        var amount = scoringDataDto.getAmount();
        var term = scoringDataDto.getTerm();
        var rateAfterReceivingStatus = changeRateDependingOnConditions(scoringDataDto);
        var monthlyPayment = calculateMonthlyPayment(amount, term, rateAfterReceivingStatus);
        var psk = calculatePsk(amount, term, rateAfterReceivingStatus);
        var interestPayment = psk.multiply(rateAfterReceivingStatus.divide(new BigDecimal(12), RoundingMode.HALF_UP));

        return CreditDto.builder()
                .amount(scoringDataDto.getAmount())
                .term(scoringDataDto.getTerm())
                .monthlyPayment(BigDecimal.valueOf(scoringDataDto.getTerm())) // Исправлено на корректное значение
                .rate(rateAfterReceivingStatus)
                .psk(psk)
                .isInsuranceEnabled(scoringDataDto.getIsInsuranceEnabled())
                .isSalaryClient(scoringDataDto.getIsSalaryClient())
                .paymentSchedule(List.of(PaymentScheduleElementDto.builder()
                        .number(1)
                        .date(LocalDate.now().plusMonths(1))
                        .totalPayment(psk)
                        .interestPayment(interestPayment)
                        .debtPayment(monthlyPayment)
                        .remainingDebt(psk)
                        .build()))
                .build();
    }

    private BigDecimal changeRateDependingOnConditions(ScoringDataDto scoringDataDto) {
        var baseRate = BASE_RATE;
        var salary = scoringDataDto.getEmploymentDto().get(0).getSalary();
        var emplStatus = scoringDataDto.getEmploymentDto().get(0).getEmploymentStatus();
        var positionAtWork = scoringDataDto.getEmploymentDto().get(0).getPosition();

        switch (emplStatus) {
            case UNEMPLOYED:
                throw new ForbiddenException(String.format(CREDITOR_STATUS, emplStatus));
            case SELF_EMPLOYED:
                baseRate = baseRate.add(BigDecimal.ONE);
            default:
                break;
        }

        switch (positionAtWork) {
            case MID_MANAGER:
                baseRate = baseRate.subtract(BigDecimal.valueOf(2));
            case TOP_MANAGER:
                baseRate = baseRate.subtract(BigDecimal.valueOf(3));
            default:
                break;
        }

        if (scoringDataDto.getAmount().compareTo(new BigDecimal(25).multiply(salary)) > 0) {
            throw new ForbiddenException(String.format(SALARY_AMOUNT, salary));
        }

        switch (scoringDataDto.getMaritalStatus()) {
            case MARRIED:
                baseRate = baseRate.subtract(BigDecimal.valueOf(3));
            case SINGLE:
                baseRate = baseRate.add(BigDecimal.ONE);
            default:
                break;
        }

        var age = getAge(scoringDataDto.getBirthdate());

        if (age < 18 || age > 65) {
            throw new ForbiddenException(String.format(AGE_VERIFICATION, age));
        }

        if (scoringDataDto.getGender() == Gender.FEMALE && age >= 32 && age <= 60) {
            baseRate = baseRate.subtract(BigDecimal.valueOf(3));
        } else if (scoringDataDto.getGender() == Gender.MALE && age >= 30 && age <= 55) {
            baseRate = baseRate.subtract(BigDecimal.valueOf(3));
        } else if (!scoringDataDto.getGender().equals(Gender.MALE) &&!scoringDataDto.getGender().equals(Gender.FEMALE)) {
            baseRate = baseRate.add(BigDecimal.valueOf(7));
        }

        var workExperienceTotal = scoringDataDto.getEmploymentDto().get(0).getWorkExperienceTotal();
        var workExperienceCurrent = scoringDataDto.getEmploymentDto().get(0).getWorkExperienceCurrent();

        if (workExperienceTotal < 18 || workExperienceCurrent < 3) {
            throw new ForbiddenException(WORK_EXPERIENCE);
        }

        return baseRate;
    }

    private BigDecimal calculatePsk(BigDecimal amount, Integer term, BigDecimal rate) {
        return amount.multiply(rate).divide(BigDecimal.valueOf((term * 12)), RoundingMode.HALF_UP);
    }

    private BigDecimal calculateMonthlyPayment(BigDecimal amount, Integer term, BigDecimal rate) {
        var monthlyRate = rate.divide(new BigDecimal(100), RoundingMode.HALF_UP);
        var termInMonth = term * 12;
        return amount.multiply(monthlyRate).divide(BigDecimal.valueOf(termInMonth), RoundingMode.HALF_UP).pow(2).divide(BigDecimal.valueOf(termInMonth), RoundingMode.HALF_UP).subtract(BigDecimal.valueOf(termInMonth)).pow(2);
    }

    private int getAge(LocalDate birthdate) {
        if (birthdate!= null) {
            return Period.between(birthdate, LocalDate.now()).getYears();
        } else {
            return 0;
        }
    }

}