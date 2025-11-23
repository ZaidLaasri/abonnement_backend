package com.saas.abonnement.abonnement_backend.subscriber.mapper;

import org.springframework.stereotype.Component;

import com.saas.abonnement.abonnement_backend.clientapp.persistence.ClientAppEntity;
import com.saas.abonnement.abonnement_backend.subscriber.dto.SubscriberDto;
import com.saas.abonnement.abonnement_backend.subscriber.persistence.SubscriberEntity;

@Component
public class SubscriberMapper {

    public SubscriberDto toDto(SubscriberEntity entity) {
        if (entity == null) return null;

        return SubscriberDto.builder()
                .id(entity.getId())
                .clientAppId(entity.getClientApp().getId())
                .externalUserId(entity.getExternalUserId())
                .email(entity.getEmail())
                .status(entity.getStatus())
                .build();
    }

    public SubscriberEntity toEntity(SubscriberDto dto, ClientAppEntity clientApp) {
        if (dto == null) return null;

        return SubscriberEntity.builder()
                .clientApp(clientApp)
                .externalUserId(dto.getExternalUserId())
                .email(dto.getEmail())
                .status(dto.getStatus())
                .build();
    }
}
