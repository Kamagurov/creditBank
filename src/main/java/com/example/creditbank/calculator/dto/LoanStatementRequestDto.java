package com.example.creditbank.calculator.dto;

import com.example.creditbank.calculator.validation.constraints.LocalDateValidation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
public class LoanStatementRequestDto {

    @Schema(description = "Сумма кредита")
    @DecimalMin(message = "Сумма кредита должна быть большее 30 000.", value = "30000")
    private BigDecimal amount;

    @Schema(description = "Срок кредита")
    @Min(message = "Срок кредита должен быть большее 6 (в месяцах).", value = 6)
    private Integer term;

    @Schema(description = "Имя", example = "Петр")
    @Pattern(message = "Имя не соответствует формату. Пример: Петр",
            regexp = "^[А-Яа-я]{2,30}$")
    private String firstName;

    @Schema(description = "Фамилия", example = "Тополев")
    @Pattern(message = "Фамилия не соответствует формату. Пример: Тополев",
            regexp = "^[А-Яа-я]{2,30}$")
    @Size(min = 2, max = 30, message = "Фамилия должна быть от 2 до 30 символов.")
    private String lastName;

    @Schema(description = "Отчество", example = "Михайлович")
    @Pattern(message = "Отчество не соответствует формату. Пример: Михайлович",
            regexp = "^[А-Яа-я]{2,30}$")
    @Size(min = 2, max = 30, message = "Отчество должно быть от 2 до 30 символов.")
    private String middleName;

    @Schema(description = "Электронная почта")
    @Email(message = "Email адрес не соответствует формату. Пример: firstName@mail.ru",
            regexp = "^[a-zA-Z0-9_#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    private String email;

    @Schema(description = "Дата рождения")
    @LocalDateValidation(message = "Возраст должен быть больше или равен 18 годам.")
    private LocalDate birthdate;

    @Schema(description = "Серия паспорта")
    @Size(min = 4, max = 4, message = "Серия паспорта должна состоять из 4 цифр.")
    private String passportSeries;

    @Schema(description = "Номер паспорта")
    @Size(min = 6, max = 6, message = "Номер паспорта должен состоять из 6 цифр.")
    private String passportNumber;
}
