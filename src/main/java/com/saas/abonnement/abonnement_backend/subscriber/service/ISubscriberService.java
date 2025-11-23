package com.saas.abonnement.abonnement_backend.subscriber.service;

import java.util.List;

import com.saas.abonnement.abonnement_backend.subscriber.dto.SubscriberDto;

public interface ISubscriberService {
 SubscriberDto createSubscriber(Long clientAppId, SubscriberDto subscriberDto);

    List<SubscriberDto> getSubscribersByClientApp(Long clientAppId);

    SubscriberDto getSubscriber(Long clientAppId, String externalUserId);
}