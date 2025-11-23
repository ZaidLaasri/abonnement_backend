package com.saas.abonnement.abonnement_backend.plan.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.saas.abonnement.abonnement_backend.clientapp.persistence.ClientAppEntity;
import com.saas.abonnement.abonnement_backend.clientapp.persistence.ClientAppRepository;
import com.saas.abonnement.abonnement_backend.plan.dto.PlanDto;
import com.saas.abonnement.abonnement_backend.plan.mapper.PlanMapper;
import com.saas.abonnement.abonnement_backend.plan.persistence.PlanEntity;
import com.saas.abonnement.abonnement_backend.plan.persistence.PlanRepository;
import com.saas.abonnement.abonnement_backend.plan.service.IPlanService;

@Service
@Transactional
public class PlanServiceImpl implements IPlanService {

    private final PlanRepository planRepository;
    private final ClientAppRepository clientAppRepository;
    private final PlanMapper mapper;

    public PlanServiceImpl(
            PlanRepository planRepository,
            ClientAppRepository clientAppRepository,
            PlanMapper mapper) {
        this.planRepository = planRepository;
        this.clientAppRepository = clientAppRepository;
        this.mapper = mapper;
    }

    @Override
    public PlanDto createPlan(PlanDto dto) {

        ClientAppEntity clientApp = clientAppRepository.findById(dto.getClientAppId())
                .orElseThrow(() -> new IllegalArgumentException("ClientApp not found"));

        PlanEntity entity = mapper.toEntity(dto, clientApp);

        PlanEntity saved = planRepository.save(entity);

        return mapper.toDto(saved);
    }

    @Override
    public List<PlanDto> getPlansByClient(Long clientAppId) {


        ClientAppEntity clientApp = clientAppRepository.findById(clientAppId)
                .orElseThrow(() -> new IllegalArgumentException("ClientApp not found"));

       return planRepository.findByClientApp(clientApp)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public PlanDto getPlanDetails(Long clientAppId, String planCode) {
     
        ClientAppEntity clientApp = clientAppRepository.findById(clientAppId)
                .orElseThrow(() -> new IllegalArgumentException("ClientApp not found"));

        PlanEntity plan = planRepository.findByClientAppAndCode(clientApp, planCode)
                .orElseThrow(() -> new IllegalArgumentException("Plan not found"));

        return mapper.toDto(plan);
    }
}