package com.saas.abonnement.abonnement_backend.invoice.service;

import java.util.List;

import com.saas.abonnement.abonnement_backend.invoice.dto.InvoiceDto;

public interface IInvoiceService {

    InvoiceDto createInvoice(Long subscriptionId);

    InvoiceDto markAsPaid(Long invoiceId);

    List<InvoiceDto> getInvoicesBySubscription(Long subscriptionId);
}