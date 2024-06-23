package com.example.creditbank.deal.repositories;

import com.example.creditbank.deal.model.entity.StatementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StatementRepository extends JpaRepository<StatementEntity, Long> {

    Optional<StatementEntity> findStatementEntitiesByStatementId(UUID statementId);
}
