package com.saas.abonnement.abonnement_backend.subscription.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long> {

    List<SubscriptionEntity> findBySubscriberId(Long subscriberId);

    List<SubscriptionEntity> findByPlanId(Long planId);
}