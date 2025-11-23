package com.saas.abonnement.abonnement_backend.plan.mapper;

import com.saas.abonnement.abonnement_backend.clientapp.persistence.ClientAppEntity;
import com.saas.abonnement.abonnement_backend.plan.dto.PlanDto;
import com.saas.abonnement.abonnement_backend.plan.persistence.PlanEntity;
import com.saas.abonnement.abonnement_backend.plan.persistence.PlanStatus;
import org.springframework.stereotype.Component;

@Component public class PlanMapper { 
    public PlanDto toDto(PlanEntity entity) { 
        if (entity == null) return null; 
        return PlanDto.builder() .id(entity.getId()) .clientAppId(entity.getClientApp().getId()) .code(entity.getCode()) .name(entity.getName()) .description(entity.getDescription()) .price(entity.getPrice()) .currency(entity.getCurrency()) .durationInDays(entity.getDurationInDays()) .status(entity.getStatus().name()) .build(); 
    } public PlanEntity toEntity(PlanDto dto, ClientAppEntity clientApp) { if (dto == null) return null; PlanEntity entity = new PlanEntity(); entity.setClientApp(clientApp); entity.setCode(dto.getCode()); entity.setName(dto.getName()); entity.setDescription(dto.getDescription()); entity.setPrice(dto.getPrice()); entity.setCurrency(dto.getCurrency()); entity.setDurationInDays(dto.getDurationInDays()); if (dto.getStatus() != null) { entity.setStatus(PlanStatus.valueOf(dto.getStatus())); } return entity; } }