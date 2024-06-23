package com.example.creditbank.deal.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Passport implements Serializable {

    private String series;

    private String number;

    private String issueBranch;

    private LocalDateTime issueDate;
}
