package com.saas.abonnement.abonnement_backend.subscription.web;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saas.abonnement.abonnement_backend.subscription.dto.SubscriptionDto;
import com.saas.abonnement.abonnement_backend.subscription.service.ISubscriptionService;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final ISubscriptionService service;

    public SubscriptionController(ISubscriptionService service) {
        this.service = service;
    }

    @PostMapping
    public SubscriptionDto create(
            @RequestParam Long subscriberId,
            @RequestParam Long planId,
            @RequestBody SubscriptionDto dto
    ) {
        return service.createSubscription(subscriberId, planId, dto);
    }

    @GetMapping("/{id}")
    public SubscriptionDto get(@PathVariable Long id) {
        return service.getSubscription(id);
    }

    @GetMapping("/by-subscriber/{subscriberId}")
    public List<SubscriptionDto> getBySubscriber(@PathVariable Long subscriberId) {
        return service.getSubscriptionsBySubscriber(subscriberId);
    }
}
