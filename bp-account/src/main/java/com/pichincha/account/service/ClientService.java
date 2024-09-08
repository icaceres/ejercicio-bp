package com.pichincha.account.service;

import com.pichincha.account.service.dto.ClientDto;
import reactor.core.publisher.Mono;

public interface ClientService {
    Mono<ClientDto> getClientByIdentification(String identification);
}
