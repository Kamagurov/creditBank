package com.example.creditbank.deal.mapper;

import com.example.creditbank.deal.enums.CreditStatus;
import com.example.creditbank.deal.model.dto.CreditDto;
import com.example.creditbank.deal.model.entity.CreditEntity;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreditMapper {

    private final ModelMapper modelMapper;

    public CreditEntity toEntity(CreditDto creditDto) {
        return modelMapper.map(creditDto, CreditEntity.class);
    }

    public CreditDto toDto(CreditEntity credit) {
        return modelMapper.map(credit, CreditDto.class);
    }

    public CreditEntity createCreditEntity(com.example.creditbank.calculator.dto.CreditDto creditDtoFromCalculatorService) {
        return toEntity(CreditDto.builder()
                .amount(creditDtoFromCalculatorService.getAmount())
                .term(creditDtoFromCalculatorService.getTerm())
                .rate(creditDtoFromCalculatorService.getRate())
                .psk(creditDtoFromCalculatorService.getPsk())
                .isInsuranceEnabled(creditDtoFromCalculatorService.getIsInsuranceEnabled())
                .isSalaryClient(creditDtoFromCalculatorService.getIsSalaryClient())
                .monthlyPayment(creditDtoFromCalculatorService.getMonthlyPayment())
                .paymentSchedule(null)
                .creditStatus(CreditStatus.CALCULATED)
                .build());
    }
}
