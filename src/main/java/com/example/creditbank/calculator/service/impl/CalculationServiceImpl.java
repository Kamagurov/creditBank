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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CalculationServiceImpl implements CalculationService {

    private static final String CREDITOR_STATUS = "Статус заёмщика %s = %s не удовлетворяет условиям.";
    private static final String SALARY_AMOUNT = "Зарплата %s = %s не удовлетворяет условиям.";
    private static final String AGE_VERIFICATION = "Возраст %s = %s не удовлетворяет условиям.";
    private static final String WORK_EXPERIENCE = "Стаж работы не удовлетворяет условиям.";


    private final BigDecimal BASE_RATE = BigDecimal.valueOf(18L);

    @Override
    public List<LoanOfferDto> getOffers(LoanStatementRequestDto loanStatementRequestDto) {
        List<LoanOfferDto> offers = new ArrayList<>();

        var requestAmount = loanStatementRequestDto.getAmount();
        var term = loanStatementRequestDto.getTerm();

        boolean[] combinations = {false, false, false, true, false, true, true, true};

        for (boolean insurance : combinations) {
            for (boolean salaryClient : combinations) {

                // Создаем новое предложение на основе текущих комбинаций
                var rate = calculateRate(insurance, salaryClient);
                var totalAmount = calculateTotalAmount(requestAmount, insurance, salaryClient);
                var monthlyPayment = calculateMonthlyPayment(totalAmount, term, rate);

                offers.add(LoanOfferDto.builder()
                        .requestedAmount(requestAmount)
                        .totalAmount(totalAmount)
                        .term(term)
                        .monthlyPayment(monthlyPayment)
                        .rate(rate)
                        .isInsuranceEnabled(insurance)
                        .isSalaryClient(salaryClient)
                        .build());
            }
        }

        // Сортировка предложений по возрастанию ставки
        offers.sort(Comparator.comparing(LoanOfferDto::getRate));

        return offers;
    }

    /*
    *   Расчет процентной ставки на основе двух параметров: isInsuranceEnabled и isSalaryClient
     */
    private BigDecimal calculateRate(boolean isInsuranceEnabled, boolean isSalaryClient) {
        var baseRate = BASE_RATE;
        if (isInsuranceEnabled) {
            baseRate = baseRate.subtract(BigDecimal.valueOf(3)); // Уменьшение на 5% за страховку
        }
        if (isSalaryClient) {
            baseRate = baseRate.subtract(BigDecimal.valueOf(1)); // Уменьшение на 3% за статус зп
        }
        return baseRate;
    }

    /*
    *   Прибавление 100к за страховку
     */
    private BigDecimal calculateTotalAmount(BigDecimal requestAmount, boolean isInsuranceEnabled, boolean isSalaryClient) {
        var totalAmount = requestAmount;
        if (isInsuranceEnabled) {
            totalAmount = totalAmount.add(BigDecimal.valueOf(100000));
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
                .monthlyPayment(BigDecimal.valueOf(scoringDataDto.getTerm()))
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

    /*
     *   Изменение ставки в зависимости от статуса сотрудника
     */
    private BigDecimal changeRateDependingOnConditions(ScoringDataDto scoringDataDto) {
        var baseRate = BASE_RATE;
        var salary = scoringDataDto.getEmploymentDto().getSalary();
        var emplStatus = scoringDataDto.getEmploymentDto().getEmploymentStatus();
        var positionAtWork = scoringDataDto.getEmploymentDto().getPosition();

        // Проверка рабочего статуса
        switch (emplStatus) {
            case UNEMPLOYED:
                throw new ForbiddenException(String.format(CREDITOR_STATUS, emplStatus)); // Отказ
            case SELF_EMPLOYED:
                baseRate = baseRate.add(BigDecimal.ONE); // Увеличение на 1%
            case BUSINESS_OWNER:
                baseRate = baseRate.add(BigDecimal.valueOf(2)); // Увеличение на 2%
        }

        // Проверка позиции на работе
        switch (positionAtWork) {
            case MID_MANAGER:
                baseRate = baseRate.subtract(BigDecimal.valueOf(2)); // Уменьшение на 1%
            case TOP_MANAGER:
                baseRate = baseRate.subtract(BigDecimal.valueOf(3)); // Уменьшение на 2%
        }

        // Проверка суммы займа
        if (scoringDataDto.getAmount().compareTo(new BigDecimal(25).multiply(salary)) > 0) {
            throw new ForbiddenException(String.format(SALARY_AMOUNT, salary)); // Отказ
        }

        // Проверка семейного положения
        switch (scoringDataDto.getMaritalStatus()) {
            case MARRIED:
                baseRate = baseRate.subtract(BigDecimal.valueOf(3)); // Уменьшение на 3%
            case SINGLE:
                baseRate = baseRate.add(BigDecimal.ONE); // Увеличение на 1%
        }

        // Проверка возраста
        var age = getAge(scoringDataDto.getBirthdate());

        if (age < 18 || age > 65) {
            throw new ForbiddenException(String.format(AGE_VERIFICATION, age)); // Отказ
        }

        // Проверка пола и возраста
        if (scoringDataDto.getGender() == Gender.FEMALE && age >= 32 && age <= 60) {
            baseRate = baseRate.subtract(BigDecimal.valueOf(3)); // Уменьшение на 3%
        } else if (scoringDataDto.getGender() == Gender.MALE && age >= 30 && age <= 55) {
            baseRate = baseRate.subtract(BigDecimal.valueOf(3)); // Уменьшение на 3%
        } else if (!scoringDataDto.getGender().equals(Gender.MALE) && !scoringDataDto.getGender().equals(Gender.FEMALE)) {
            baseRate = baseRate.add(BigDecimal.valueOf(7)); // Увеличение на 7%
        }

        var workExperienceTotal = scoringDataDto.getEmploymentDto().getWorkExperienceTotal();
        var workExperienceCurrent = scoringDataDto.getEmploymentDto().getWorkExperienceTotal();

        // Проверка стажа работы
        if (workExperienceTotal < 18 || workExperienceCurrent < 3) {
            throw new ForbiddenException(WORK_EXPERIENCE); // Отказ
        }

        return baseRate;
    }

    /*
     *   Полная стоимость кредита
     */
    private BigDecimal calculatePsk(BigDecimal amount, Integer term, BigDecimal rate) {

        return amount.multiply(rate).divide(BigDecimal.valueOf((term * 12)), RoundingMode.HALF_UP);
    }

    /*
     *   Расчет ежемесячного платежа по кредиту
     */
    private BigDecimal calculateMonthlyPayment(BigDecimal amount, Integer term, BigDecimal rate) {
        var monthlyRate = rate.divide(new BigDecimal(100), RoundingMode.HALF_UP);

        var termInMonth = term * 12;
        // Рассчитываем ежемесячный платеж по формуле аннуитетного платежа
        return amount.multiply(monthlyRate)
                .divide(BigDecimal.valueOf(termInMonth), RoundingMode.HALF_UP).pow(2)
                .divide(BigDecimal.valueOf(termInMonth), RoundingMode.HALF_UP).subtract(BigDecimal.valueOf(termInMonth)).pow(2);

    }

    /*
     *   Возраст заёмщика
     */

    private int getAge(LocalDate birthdate) {
        if (birthdate != null) {
            return Period.between(birthdate, LocalDate.now()).getYears();
        } else {
            return 0;
        }
    }

}
