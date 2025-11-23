package com.saas.abonnement.abonnement_backend.clientapp.service;

import com.saas.abonnement.abonnement_backend.clientapp.dto.ClientAppDto;

import java.util.List;

public interface ClientAppService {

    ClientAppDto register(ClientAppDto dto);

    List<ClientAppDto> getAll();

    ClientAppDto getById(Long id);
}
