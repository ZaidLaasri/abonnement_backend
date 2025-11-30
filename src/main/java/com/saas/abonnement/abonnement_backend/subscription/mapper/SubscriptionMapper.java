package com.saas.abonnement.abonnement_backend.subscription.mapper;

import org.springframework.stereotype.Component;

import com.saas.abonnement.abonnement_backend.subscription.dto.SubscriptionDto;
import com.saas.abonnement.abonnement_backend.subscription.persistence.SubscriptionEntity;

@Component
public class SubscriptionMapper {

    public SubscriptionDto toDto(SubscriptionEntity entity) {
        if (entity == null) return null;

        return SubscriptionDto.builder()
                .id(entity.getId())
                .subscriberId(entity.getSubscriber().getId())
                .planId(entity.getPlan().getId())
                .startDate(entity.getStartDate() != null ? entity.getStartDate().toString() : null)
                .endDate(entity.getEndDate() != null ? entity.getEndDate().toString() : null)
                .renewalType(entity.getRenewalType())
                .status(entity.getStatus())
                .build();
    }
}