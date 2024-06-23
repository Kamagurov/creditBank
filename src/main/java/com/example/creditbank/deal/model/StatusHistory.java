package com.example.creditbank.deal.model;

import com.example.creditbank.deal.enums.ChangeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class StatusHistory implements Serializable {

    private String status;

    private LocalDateTime time;

    private ChangeType changeType;
}
