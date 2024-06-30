package com.example.creditbank.calculator.validation.validator;

import com.example.creditbank.calculator.validation.constraints.LocalDateValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class LocalDateValidator implements ConstraintValidator<LocalDateValidation, LocalDate> {

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
        if (date == null) {
            return true;
        }

        if (date.isBefore(LocalDate.of(1, 1, 1))) {
            return false;
        }

        LocalDate now = LocalDate.now();

        Period period = Period.between(date, now);

        if (period.getYears() < 18) {
            return false;
        }

        if (date.getMonthValue() < 1 || date.getMonthValue() > 12) {
            return false;
        }
        switch (date.getMonthValue()) {
            case 2:
                if (date.getDayOfMonth() > 28) {
                    return false;
                }
                break;
            case 4, 6, 9, 11:
                if (date.getDayOfMonth() > 30) {
                    return false;
                }
                break;
            case 1, 3, 5, 7, 8, 10, 12:
                if (date.getDayOfMonth() > 31) {
                    return false;
                }
                break;
        }

        return true;
    }
}