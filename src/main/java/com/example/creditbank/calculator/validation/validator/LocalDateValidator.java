package com.example.creditbank.calculator.validation.validator;

import com.example.creditbank.calculator.validation.constraints.LocalDateValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class LocalDateValidator implements ConstraintValidator<LocalDateValidation, LocalDate> {

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
        if (date == null || date.isBefore(LocalDate.of(1, 1, 1))) {
            return false;
        }

        LocalDate now = LocalDate.now();
        Period period = Period.between(date, now);
        if (period.getYears() < 18 || date.getMonthValue() < 1 || date.getMonthValue() > 12) {
            return false;
        }

        int maxDayOfMonth = date.getMonthValue() == 2 && date.isLeapYear()? 29 : 28;

        if (date.getDayOfMonth() > maxDayOfMonth) {
            return false;
        }
        return true;
    }
}