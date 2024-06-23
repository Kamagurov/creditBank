package com.example.creditbank.deal.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class Passport implements Serializable {

    private String series;

    private String number;

    private String issueBranch;

    private LocalDate issueDate;
}
