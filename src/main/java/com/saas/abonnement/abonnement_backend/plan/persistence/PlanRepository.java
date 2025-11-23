package com.saas.abonnement.abonnement_backend.plan.persistence;

import com.saas.abonnement.abonnement_backend.clientapp.persistence.ClientAppEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlanRepository extends JpaRepository<PlanEntity, Long> {

    List<PlanEntity> findByClientApp(ClientAppEntity clientApp);

    Optional<PlanEntity> findByClientAppAndCode(ClientAppEntity clientApp, String code);
}