package com.example.creditbank.deal.mapper;

import com.example.creditbank.calculator.dto.LoanStatementRequestDto;
import com.example.creditbank.deal.model.dto.ClientDto;
import com.example.creditbank.deal.model.dto.FinishRegistrationRequestDto;
import com.example.creditbank.deal.model.entity.ClientEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientMapper {

    private ModelMapper modelMapper;

    public ClientEntity toEntity(ClientDto clientDto) {
        return modelMapper.map(clientDto, ClientEntity.class);
    }

    public ClientDto toDto(ClientEntity client) {
        return modelMapper.map(client, ClientDto.class);
    }

    public ClientEntity createClient(LoanStatementRequestDto loanStatementRequestDto, FinishRegistrationRequestDto finishRegistrationRequestDto) {
        return toEntity(ClientDto.builder()
                .firstName(loanStatementRequestDto.getFirstName())
                .lastName(loanStatementRequestDto.getLastName())
                .middleName(loanStatementRequestDto.getMiddleName())
                .birthDate(loanStatementRequestDto.getBirthdate())
                .email(loanStatementRequestDto.getEmail())
                .gender(null)
                .maritalStatus(null)
                .dependentAmount(loanStatementRequestDto.getAmount().intValue())
                .employment(null)
                .passport(null)
                .accountNumber(null)
                .build());
    }
}
