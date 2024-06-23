package com.example.creditbank.deal.model.dto;


import com.example.creditbank.calculator.enums.Gender;
import com.example.creditbank.calculator.enums.MaritalStatus;
import com.example.creditbank.deal.model.Employment;
import com.example.creditbank.deal.model.Passport;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ClientDto {

    private String firstName;

    private String lastName;

    private String middleName;

    private LocalDateTime birthDate;

    private String email;

    private Gender gender;

    private MaritalStatus maritalStatus;

    private int dependentAmount;

    private Passport passport;

    private Employment employment;

    private String accountNumber;
}
