package com.saas.abonnement.abonnement_backend.invoice.persistence;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.saas.abonnement.abonnement_backend.subscription.persistence.SubscriptionEntity;

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
@Table(name = "invoices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String invoiceNumber;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_id")
    private SubscriptionEntity subscription;

    private BigDecimal amount;

    private String currency;

    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;

    private LocalDateTime issuedAt;

    private LocalDateTime paidAt;
}