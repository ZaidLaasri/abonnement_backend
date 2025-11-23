package com.saas.abonnement.abonnement_backend.clientapp.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.saas.abonnement.abonnement_backend.clientapp.dto.ClientAppDto;
import com.saas.abonnement.abonnement_backend.clientapp.mapper.ClientAppMapper;
import com.saas.abonnement.abonnement_backend.clientapp.persistence.ClientAppEntity;
import com.saas.abonnement.abonnement_backend.clientapp.persistence.ClientAppRepository;
import com.saas.abonnement.abonnement_backend.clientapp.service.ClientAppService;

@Service
@Transactional
public class ClientAppServiceImpl implements ClientAppService {

    private final ClientAppRepository repo;
    private final ClientAppMapper mapper;

    public ClientAppServiceImpl(ClientAppRepository repo, ClientAppMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public ClientAppDto register(ClientAppDto dto) {

        if (repo.existsByName(dto.getName())) {
            throw new IllegalArgumentException("ClientApp with this name already exists");
        }

        ClientAppEntity entity = mapper.toEntity(dto);

        // ApiKey générée côté backend, jamais depuis DTO
        entity.setApiKey(UUID.randomUUID().toString());

        ClientAppEntity saved = repo.save(entity);

        return mapper.toDto(saved);
    }

    @Override
    public List<ClientAppDto> getAll() {
        return repo.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public ClientAppDto getById(Long id) {
        return repo.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Client app not found"));
    }
}