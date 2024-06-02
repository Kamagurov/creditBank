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
    private static final BigDecimal DIVIDER_PERCENT = BigDecimal.valueOf(100);
    private static final int MONTH_COUNT = 12;
    private static final int MIN_TOTAL_WORK_EXPERIENCE_IN_MONTH = 18;
    private static final int MIN_CURRENT_WORK_EXPERIENCE_IN_MONTH = 3;
    private static final int AGE_OF_MAJORITY = 18;
    private static final int RETIREMENT_AGE = 65;
    private static final int AVERAGE_AGE_OF_A_MALE = 30;
    private static final int OLD_AGE_OF_A_MALE = 55;
    private static final int AVERAGE_AGE_OF_A_FEMALE = 32;
    private static final int OLD_AGE_OF_A_FEMALE = 60;
    private static final BigDecimal SALARY_COUNT = BigDecimal.valueOf(25);
    private static final BigDecimal RATE_7 = BigDecimal.valueOf(7);
    private static final BigDecimal RATE_5 = BigDecimal.valueOf(5);
    private static final BigDecimal RATE_3 = BigDecimal.valueOf(3);
    private static final BigDecimal RATE_2 = BigDecimal.valueOf(2);

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
            baseRate = baseRate.subtract(RATE_5);
        }
        if (isSalaryClient) {
            baseRate = baseRate.subtract(RATE_3);
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
        var interestPayment = psk.multiply(rateAfterReceivingStatus.divide(new BigDecimal(MONTH_COUNT), RoundingMode.HALF_UP));

        return CreditDto.builder()
                .amount(scoringDataDto.getAmount())
                .term(scoringDataDto.getTerm())
                .monthlyPayment(BigDecimal.valueOf(scoringDataDto.getTerm()))
                .rate(rateAfterReceivingStatus)
                .psk(psk)
                .isInsuranceEnabled(scoringDataDto.getIsInsuranceEnabled())
                .isSalaryClient(scoringDataDto.getIsSalaryClient())
                .paymentSchedule(List.of(PaymentScheduleElementDto.builder()
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
                baseRate = baseRate.subtract(RATE_2);
            case TOP_MANAGER:
                baseRate = baseRate.subtract(RATE_3);
            default:
                break;
        }

        if (scoringDataDto.getAmount().compareTo(SALARY_COUNT.multiply(salary)) > 0) {
            throw new ForbiddenException(String.format(SALARY_AMOUNT, salary));
        }

        switch (scoringDataDto.getMaritalStatus()) {
            case MARRIED:
                baseRate = baseRate.subtract(RATE_3);
            case SINGLE:
                baseRate = baseRate.add(BigDecimal.ONE);
            default:
                break;
        }

        var age = getAge(scoringDataDto.getBirthdate());

        if (age < AGE_OF_MAJORITY || age > RETIREMENT_AGE) {
            throw new ForbiddenException(String.format(AGE_VERIFICATION, age));
        }

        if (scoringDataDto.getGender() == Gender.FEMALE && age >= AVERAGE_AGE_OF_A_FEMALE && age <= OLD_AGE_OF_A_FEMALE) {
            baseRate = baseRate.subtract(RATE_3);
        } else if (scoringDataDto.getGender() == Gender.MALE && age >= AVERAGE_AGE_OF_A_MALE && age <= OLD_AGE_OF_A_MALE) {
            baseRate = baseRate.subtract(RATE_3);
        } else if (!scoringDataDto.getGender().equals(Gender.MALE) &&!scoringDataDto.getGender().equals(Gender.FEMALE)) {
            baseRate = baseRate.add(RATE_7);
        }

        var workExperienceTotal = scoringDataDto.getEmploymentDto().get(0).getWorkExperienceTotal();
        var workExperienceCurrent = scoringDataDto.getEmploymentDto().get(0).getWorkExperienceCurrent();

        if (workExperienceTotal < MIN_TOTAL_WORK_EXPERIENCE_IN_MONTH || workExperienceCurrent < MIN_CURRENT_WORK_EXPERIENCE_IN_MONTH) {
            throw new ForbiddenException(WORK_EXPERIENCE);
        }

        return baseRate;
    }

    private BigDecimal calculatePsk(BigDecimal amount, Integer term, BigDecimal rate) {
        return amount.multiply(rate).divide(BigDecimal.valueOf((term * MONTH_COUNT)), RoundingMode.HALF_UP);
    }

    private BigDecimal calculateMonthlyPayment(BigDecimal amount, Integer term, BigDecimal rate) {
        var monthlyRate = rate.divide(DIVIDER_PERCENT, RoundingMode.HALF_UP);
        var termInMonth = term * MONTH_COUNT;
        return amount.multiply(monthlyRate).divide(BigDecimal.valueOf(termInMonth), RoundingMode.HALF_UP).pow(2)
                .divide(BigDecimal.valueOf(termInMonth), RoundingMode.HALF_UP).subtract(BigDecimal.valueOf(termInMonth))
                .pow(2);
    }

    private int getAge(LocalDate birthdate) {
        if (birthdate!= null) {
            return Period.between(birthdate, LocalDate.now()).getYears();
        } else {
            return 0;
        }
    }
}