package com.saas.abonnement.abonnement_backend.subscriber.web;

import com.saas.abonnement.abonnement_backend.subscriber.dto.SubscriberDto;
import com.saas.abonnement.abonnement_backend.subscriber.service.ISubscriberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client-apps/{clientAppId}/subscribers")
public class SubscriberController {

    private final ISubscriberService subscriberService;

    public SubscriberController(ISubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }

    @PostMapping
    public SubscriberDto createSubscriber(
            @PathVariable Long clientAppId,
            @RequestBody SubscriberDto dto
    ) {
        return subscriberService.createSubscriber(clientAppId, dto);
    }

    @GetMapping
    public List<SubscriberDto> getSubscribers(@PathVariable Long clientAppId) {
        return subscriberService.getSubscribersByClientApp(clientAppId);
    }

    @GetMapping("/{externalUserId}")
    public SubscriberDto getSubscriber(
            @PathVariable Long clientAppId,
            @PathVariable String externalUserId
    ) {
        return subscriberService.getSubscriber(clientAppId, externalUserId);
    }
}