package com.example.creditbank.calculator.validation.constraints;

import com.example.creditbank.calculator.validation.validator.LocalDateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LocalDateValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LocalDateValidation {
    String message() default "Дата должна соответствовать заданным условиям";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}