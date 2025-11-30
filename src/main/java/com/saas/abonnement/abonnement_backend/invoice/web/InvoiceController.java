package com.saas.abonnement.abonnement_backend.invoice.web;


import com.saas.abonnement.abonnement_backend.invoice.dto.InvoiceDto;
import com.saas.abonnement_abonnement_backend.invoice.service.IInvoiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final IInvoiceService service;

    public InvoiceController(IInvoiceService service) {
        this.service = service;
    }

    @PostMapping
    public InvoiceDto create(@RequestParam Long subscriptionId) {
        return service.createInvoice(subscriptionId);
    }

    @PostMapping("/{invoiceId}/pay")
    public InvoiceDto markAsPaid(@PathVariable Long invoiceId) {
        return service.markAsPaid(invoiceId);
    }

    @GetMapping("/subscription/{subscriptionId}")
    public List<InvoiceDto> getBySubscription(@PathVariable Long subscriptionId) {
        return service.getInvoicesBySubscription(subscriptionId);
    }
}