package com.saas.abonnement.abonnement_backend.subscription.persistence;

import java.time.LocalDateTime;

import com.saas.abonnement.abonnement_backend.plan.persistence.PlanEntity;
import com.saas.abonnement.abonnement_backend.subscriber.persistence.SubscriberEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "subscriptions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "subscriber_id")
    private SubscriberEntity subscriber;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    private PlanEntity plan;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    private RenewalType renewalType;

    @Enumerated(EnumType.STRING)
    private SubscriptionStatus status;

    private LocalDateTime createdAt;
}