package com.example.creditbank.deal.repositories;

import com.example.creditbank.deal.model.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    Optional<ClientEntity> findClientEntityByClientId(UUID clientId);
}
