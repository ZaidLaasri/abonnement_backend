package com.saas.abonnement.abonnement_backend.subscriber.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriberRepository extends JpaRepository<SubscriberEntity, Long> {

    List<SubscriberEntity> findByClientAppId(Long clientAppId);

    Optional<SubscriberEntity> findByClientAppIdAndExternalUserId(Long clientAppId, String externalUserId);

    boolean existsByClientAppIdAndEmail(Long clientAppId, String email);
}