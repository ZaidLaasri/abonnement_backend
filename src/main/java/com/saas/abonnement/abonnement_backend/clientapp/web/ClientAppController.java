package com.saas.abonnement.abonnement_backend.clientapp.web;

import com.saas.abonnement.abonnement_backend.clientapp.dto.ClientAppDto;
import com.saas.abonnement.abonnement_backend.clientapp.service.ClientAppService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client-apps")
public class ClientAppController {

    private final ClientAppService service;

    public ClientAppController(ClientAppService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientAppDto register(@RequestBody ClientAppDto dto) {
        return service.register(dto);
    }

    @GetMapping
    public List<ClientAppDto> listAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ClientAppDto getById(@PathVariable Long id) {
        return service.getById(id);
    }
}
