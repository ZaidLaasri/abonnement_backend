package com.saas.abonnement.abonnement_backend.clientapp.mapper;



import org.springframework.stereotype.Component;

import com.saas.abonnement.abonnement_backend.clientapp.dto.ClientAppDto;
import com.saas.abonnement.abonnement_backend.clientapp.persistence.ClientAppEntity;
import com.saas.abonnement.abonnement_backend.clientapp.persistence.ClientAppStatus;

@Component
public class ClientAppMapper {

    public ClientAppDto toDto(ClientAppEntity entity) {
        if (entity == null) {
            return null;
        }

        return ClientAppDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .apiKey(entity.getApiKey())
                .contactEmail(entity.getContactEmail())
                .status(entity.getStatus().name())
                .build();
    }

    public ClientAppEntity toEntity(ClientAppDto dto) {
        if (dto == null) {
            return null;
        }

        ClientAppEntity entity = new ClientAppEntity();
        entity.setName(dto.getName());
        entity.setContactEmail(dto.getContactEmail());

        if (dto.getStatus() != null) {
            entity.setStatus(ClientAppStatus.valueOf(dto.getStatus()));
        }

        return entity;
    }
}