package com.saas.abonnement.abonnement_backend.subscription.dto;

import com.saas.abonnement.abonnement_backend.subscription.persistence.RenewalType;
import com.saas.abonnement.abonnement_backend.subscription.persistence.SubscriptionStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionDto {

    private Long id;

    private Long subscriberId;
    private Long planId;

    private String startDate;
    private String endDate;

    private RenewalType renewalType;
    private SubscriptionStatus status;
}