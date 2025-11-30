package com.saas.abonnement.abonnement_backend.subscription.service.impl;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.saas.abonnement.abonnement_backend.plan.persistence.PlanEntity;
import com.saas.abonnement.abonnement_backend.plan.persistence.PlanRepository;
import com.saas.abonnement.abonnement_backend.subscriber.persistence.SubscriberEntity;
import com.saas.abonnement.abonnement_backend.subscriber.persistence.SubscriberRepository;
import com.saas.abonnement.abonnement_backend.subscription.dto.SubscriptionDto;
import com.saas.abonnement.abonnement_backend.subscription.mapper.SubscriptionMapper;
import com.saas.abonnement.abonnement_backend.subscription.persistence.RenewalType;
import com.saas.abonnement.abonnement_backend.subscription.persistence.SubscriptionEntity;
import com.saas.abonnement.abonnement_backend.subscription.persistence.SubscriptionRepository;
import com.saas.abonnement.abonnement_backend.subscription.persistence.SubscriptionStatus;
import com.saas.abonnement.abonnement_backend.subscription.service.ISubscriptionService;

@Service
@Transactional
public class SubscriptionServiceImpl implements ISubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriberRepository subscriberRepository;
    private final PlanRepository planRepository;
    private final SubscriptionMapper mapper;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository,
                                   SubscriberRepository subscriberRepository,
                                   PlanRepository planRepository,
                                   SubscriptionMapper mapper) {
        this.subscriptionRepository = subscriptionRepository;
        this.subscriberRepository = subscriberRepository;
        this.planRepository = planRepository;
        this.mapper = mapper;
    }

    @Override
    public SubscriptionDto createSubscription(Long subscriberId, Long planId, SubscriptionDto dto) {

        SubscriberEntity subscriber = subscriberRepository.findById(subscriberId)
                .orElseThrow(() -> new RuntimeException("Subscriber not found"));

        PlanEntity plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        // Vérifier que plan et subscriber appartiennent au même ClientApp
        if (!subscriber.getClientApp().getId().equals(plan.getClientApp().getId())) {
            throw new RuntimeException("Subscriber and Plan must belong to the same ClientApp");
        }

        LocalDateTime now = LocalDateTime.now();

        SubscriptionEntity entity = SubscriptionEntity.builder()
                .subscriber(subscriber)
                .plan(plan)
                .startDate(now)
                .endDate(now.plusDays(plan.getDurationInDays()))
                .renewalType(dto.getRenewalType() != null ? dto.getRenewalType() : RenewalType.AUTO)
                .status(SubscriptionStatus.ACTIVE)
                .createdAt(now)
                .build();

        return mapper.toDto(subscriptionRepository.save(entity));
    }

    @Override
    public SubscriptionDto getSubscription(Long id) {
        return subscriptionRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));
    }

    @Override
    public List<SubscriptionDto> getSubscriptionsBySubscriber(Long subscriberId) {
        return subscriptionRepository.findBySubscriberId(subscriberId)
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}