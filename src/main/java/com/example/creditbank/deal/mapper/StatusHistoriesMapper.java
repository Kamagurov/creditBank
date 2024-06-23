package com.example.creditbank.deal.mapper;

import com.example.creditbank.deal.enums.ChangeType;
import com.example.creditbank.deal.model.StatusHistory;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@RequiredArgsConstructor
public class StatusHistoriesMapper {

    private ModelMapper modelMapper;

    public StatusHistory createStatusHistory(String status, ChangeType changeType) {
        return StatusHistory.builder()
                .status(status)
                .time(null)
                .changeType(changeType)
                .build();
    }

    public List<StatusHistory> createStatusHistories(StatusHistory statusHistory) {
        return List.of(statusHistory);
    }
}
