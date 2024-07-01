package com.example.creditbank.deal.model.entity;

import com.example.creditbank.calculator.dto.EmploymentDto;
import com.example.creditbank.calculator.enums.Gender;
import com.example.creditbank.calculator.enums.MaritalStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table
public class FinishRegistrationRequestEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "marital_status")
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    @Column(name = "dependent_amount")
    private Integer dependentAmount;

    @Column(name = "passport_issue_date")
    private LocalDateTime passportIssueDate;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "employment", columnDefinition = "jsonb")
    private EmploymentDto employment;

    @Column(name = "account_number")
    private String accountNumber;
}
