package com.example.creditbank.deal.repositories;

import com.example.creditbank.deal.model.entity.CreditEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CreditRepository extends JpaRepository<CreditEntity, Long> {

    Optional<CreditEntity> findCreditEntityByCreditId(UUID creditId);
}
