//package com.example.creditbank.calculator.mapper;
//
//import com.example.creditbank.calculator.dto.CreditDto;
//import com.example.creditbank.calculator.entity.CreditEntity;
//import lombok.RequiredArgsConstructor;
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class CreditMapper {
//
//    private final ModelMapper modelMapper;
//
//    public CreditEntity toEntity(CreditDto creditDto) {
//        return modelMapper.map(creditDto, CreditEntity.class);
//    }
//
//    public CreditDto toDto(CreditEntity creditEntity) {
//        return modelMapper.map(creditEntity, CreditDto.class);
//    }
//
//}
