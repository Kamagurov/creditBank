package com.example.creditbank.deal.mapper;

import com.example.creditbank.calculator.dto.LoanStatementRequestDto;
import com.example.creditbank.deal.model.dto.StatementDto;
import com.example.creditbank.deal.model.entity.ClientEntity;
import com.example.creditbank.deal.model.entity.StatementEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StatementMapper {

    private final ModelMapper modelMapper;

    public StatementEntity toEntity(StatementDto statementDto) {
        return modelMapper.map(statementDto, StatementEntity.class);
    }

    public StatementDto toDto(StatementEntity statement) {
        return modelMapper.map(statement, StatementDto.class);
    }

    public StatementEntity createStatementEntity(LoanStatementRequestDto loanStatementRequestDto, ClientEntity clientEntity) {
        return toEntity(StatementDto.builder()
                .creditId(null)
                .clientId(clientEntity.getClientId())
                .status(null)
                .creationDate(null)
                .appliedOffer(null)
                .signDate(null)
                .sesCode(null)
                .statusHistories(null)
                .build());
    }
}
