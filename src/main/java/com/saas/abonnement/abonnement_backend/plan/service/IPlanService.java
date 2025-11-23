package com.saas.abonnement.abonnement_backend.plan.service;

import com.saas.abonnement.abonnement_backend.plan.dto.PlanDto;

import java.util.List;

public interface IPlanService {

    PlanDto createPlan(PlanDto dto);

    List<PlanDto> getPlansByClient(Long clientAppId);

    PlanDto getPlanDetails(Long clientAppId, String planCode);
}