package com.saas.abonnement.abonnement_backend.invoice.service.impl;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.saas.abonnement.abonnement_backend.invoice.dto.InvoiceDto;
import com.saas.abonnement.abonnement_backend.invoice.mapper.InvoiceMapper;
import com.saas.abonnement.abonnement_backend.invoice.persistence.InvoiceEntity;
import com.saas.abonnement.abonnement_backend.invoice.persistence.InvoiceRepository;
import com.saas.abonnement.abonnement_backend.invoice.persistence.InvoiceStatus;
import com.saas.abonnement.abonnement_backend.invoice.service.IInvoiceService;
import com.saas.abonnement.abonnement_backend.subscription.persistence.SubscriptionEntity;
import com.saas.abonnement.abonnement_backend.subscription.persistence.SubscriptionRepository;

@Service
@Transactional
public class InvoiceServiceImpl implements IInvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final InvoiceMapper mapper;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository,
                              SubscriptionRepository subscriptionRepository,
                              InvoiceMapper mapper) {
        this.invoiceRepository = invoiceRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.mapper = mapper;
    }

    @Override
    public InvoiceDto createInvoice(Long subscriptionId) {

        SubscriptionEntity subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));

        InvoiceEntity invoice = InvoiceEntity.builder()
                .subscription(subscription)
                .amount(subscription.getPlan().getPrice())   // montant du plan
                .currency(subscription.getPlan().getCurrency())
                .status(InvoiceStatus.UNPAID)
                .issuedAt(LocalDateTime.now())
                .invoiceNumber("INV-" + UUID.randomUUID())
                .build();

        return mapper.toDto(invoiceRepository.save(invoice));
    }

    @Override
    public InvoiceDto markAsPaid(Long invoiceId) {
        InvoiceEntity invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        invoice.setStatus(InvoiceStatus.PAID);
        invoice.setPaidAt(LocalDateTime.now());

        return mapper.toDto(invoiceRepository.save(invoice));
    }

    @Override
    public List<InvoiceDto> getInvoicesBySubscription(Long subscriptionId) {
        return invoiceRepository.findBySubscriptionId(subscriptionId)
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}