package com.saas.abonnement.abonnement_backend.invoice.dto;

import java.math.BigDecimal;

import com.saas.abonnement.abonnement_backend.invoice.persistence.InvoiceStatus;

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
public class InvoiceDto {

    private Long id;
    private Long subscriptionId;

    private String invoiceNumber;

    private BigDecimal amount;
    private String currency;

    private InvoiceStatus status;

    private String issuedAt;
    private String paidAt;
}