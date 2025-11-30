package com.saas.abonnement.abonnement_backend.invoice.mapper;

import org.springframework.stereotype.Component;

import com.saas.abonnement.abonnement_backend.invoice.dto.InvoiceDto;
import com.saas.abonnement.abonnement_backend.invoice.persistence.InvoiceEntity;

@Component
public class InvoiceMapper {

    public InvoiceDto toDto(InvoiceEntity entity) {
        if (entity == null) return null;

        return InvoiceDto.builder()
                .id(entity.getId())
                .subscriptionId(entity.getSubscription().getId())
                .invoiceNumber(entity.getInvoiceNumber())
                .amount(entity.getAmount())
                .currency(entity.getCurrency())
                .status(entity.getStatus())
                .issuedAt(entity.getIssuedAt() != null ? entity.getIssuedAt().toString() : null)
                .paidAt(entity.getPaidAt() != null ? entity.getPaidAt().toString() : null)
                .build();
    }
}