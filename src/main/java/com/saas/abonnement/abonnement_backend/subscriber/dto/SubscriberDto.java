package com.saas.abonnement.abonnement_backend.subscriber.dto;

import com.saas.abonnement.abonnement_backend.subscriber.persistence.SubscriberStatus;

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
public class SubscriberDto {

    private Long id;
    private Long clientAppId;
    private String externalUserId;
    private String email;
    private SubscriberStatus status;
}
