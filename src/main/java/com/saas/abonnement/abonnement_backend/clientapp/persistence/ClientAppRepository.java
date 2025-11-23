package com.saas.abonnement.abonnement_backend.clientapp.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientAppRepository extends JpaRepository<ClientAppEntity, Long> {

    Optional<ClientAppEntity> findByApiKey(String apiKey);

    boolean existsByName(String name);
}
