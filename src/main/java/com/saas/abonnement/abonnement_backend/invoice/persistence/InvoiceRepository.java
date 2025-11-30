package com.saas.abonnement.abonnement_backend.invoice.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {

    List<InvoiceEntity> findBySubscriptionId(Long subscriptionId);
}