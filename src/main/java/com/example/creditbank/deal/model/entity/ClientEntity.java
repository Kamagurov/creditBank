package com.example.creditbank.deal.model.entity;

import com.example.creditbank.calculator.enums.Gender;
import com.example.creditbank.calculator.enums.MaritalStatus;
import com.example.creditbank.deal.model.Employment;
import com.example.creditbank.deal.model.Passport;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "client")
public class ClientEntity {

    @Id
    @Column(name = "client_id")
    @GeneratedValue
    @UuidGenerator
    private UUID clientId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "middle_name", nullable = false)
    private String middleName;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "maritalStatus")
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    @Column(name = "dependent_amount")
    private int dependentAmount;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "passport", columnDefinition = "jsonb")
    private Passport passportId;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "employment_id", columnDefinition = "jsonb")
    private Employment employmentId;

    @Column(name = "account_number")
    private String accountNumber;
}
