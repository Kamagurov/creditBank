package com.example.creditbank.deal.model;

import com.example.creditbank.deal.enums.ChangeType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class StatusHistory implements Serializable {

    private String status;

    private LocalDate time;

    private ChangeType changeType;
}
