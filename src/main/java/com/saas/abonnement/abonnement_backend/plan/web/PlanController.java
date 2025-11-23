package com.saas.abonnement.abonnement_backend.plan.web;


import com.saas.abonnement.abonnement_backend.plan.dto.PlanDto;
import com.saas.abonnement.abonnement_backend.plan.service.IPlanService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plans")
public class PlanController {

    private final IPlanService service;

    public PlanController(IPlanService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlanDto createPlan(@RequestBody PlanDto dto) {
        return service.createPlan(dto);
    }

    @GetMapping("/client/{clientAppId}")
    public List<PlanDto> getPlansByClient(@PathVariable Long clientAppId) {
        return service.getPlansByClient(clientAppId);
    }

    @GetMapping("/{clientAppId}/{planCode}")
    public PlanDto getPlanDetails(@PathVariable Long clientAppId, @PathVariable String planCode) {
        return service.getPlanDetails(clientAppId, planCode);
    }
}