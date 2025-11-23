package com.saas.abonnement.abonnement_backend.subscriber.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.saas.abonnement.abonnement_backend.clientapp.persistence.ClientAppEntity;
import com.saas.abonnement.abonnement_backend.clientapp.persistence.ClientAppRepository;
import com.saas.abonnement.abonnement_backend.subscriber.dto.SubscriberDto;
import com.saas.abonnement.abonnement_backend.subscriber.mapper.SubscriberMapper;
import com.saas.abonnement.abonnement_backend.subscriber.persistence.SubscriberEntity;
import com.saas.abonnement.abonnement_backend.subscriber.persistence.SubscriberRepository;
import com.saas.abonnement.abonnement_backend.subscriber.service.ISubscriberService;

@Service
@Transactional
public class SubscriberServiceImpl implements ISubscriberService{
 
    private final SubscriberRepository subscriberRepository;
    private final ClientAppRepository clientAppRepository;
    private final SubscriberMapper subscriberMapper;

    public SubscriberServiceImpl(SubscriberRepository subscriberRepository,
                                 ClientAppRepository clientAppRepository,
                                 SubscriberMapper subscriberMapper) {
        this.subscriberRepository = subscriberRepository;
        this.clientAppRepository = clientAppRepository;
        this.subscriberMapper = subscriberMapper;
    }

    @Override
    public SubscriberDto createSubscriber(Long clientAppId, SubscriberDto dto) {
        ClientAppEntity clientApp = clientAppRepository.findById(clientAppId)
                .orElseThrow(() -> new RuntimeException("ClientApp not found"));

        boolean exists = subscriberRepository.existsByClientAppIdAndEmail(clientAppId, dto.getEmail());
        if (exists) {
            throw new RuntimeException("Subscriber already exists for this ClientApp");
        }

        SubscriberEntity entity = subscriberMapper.toEntity(dto, clientApp);
        entity.setCreatedAt(LocalDateTime.now());

        return subscriberMapper.toDto(subscriberRepository.save(entity));
    }

    @Override
    public List<SubscriberDto> getSubscribersByClientApp(Long clientAppId) {
        return subscriberRepository.findByClientAppId(clientAppId)
                .stream()
                .map(subscriberMapper::toDto)
                .toList();
    }

    @Override
    public SubscriberDto getSubscriber(Long clientAppId, String externalUserId) {
        return subscriberRepository.findByClientAppIdAndExternalUserId(clientAppId, externalUserId)
                .map(subscriberMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Subscriber not found"));
    }
}
