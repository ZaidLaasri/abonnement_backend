package com.saas.abonnement.abonnement_backend.subscription.service;

import java.util.List;

import com.saas.abonnement.abonnement_backend.subscription.dto.SubscriptionDto;

public interface ISubscriptionService {

    SubscriptionDto createSubscription(Long subscriberId, Long planId, SubscriptionDto dto);

    SubscriptionDto getSubscription(Long id);

    List<SubscriptionDto> getSubscriptionsBySubscriber(Long subscriberId);
}