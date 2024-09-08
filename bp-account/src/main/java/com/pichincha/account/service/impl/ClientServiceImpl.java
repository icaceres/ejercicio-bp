package com.pichincha.account.service.impl;

import com.pichincha.account.exception.ResourceNotFoundException;
import com.pichincha.account.service.ClientService;
import com.pichincha.account.service.dto.ClientDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceImpl implements ClientService {

    private WebClient.Builder webClientBuilder;

    public ClientServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Value("${client.service.url}")
    private String clientServiceUrl;

    @Override
    public Mono<ClientDto> getClientByIdentification(String identification) {
        return webClientBuilder.build()
                .get()
                .uri(clientServiceUrl + "/clientes/" + identification)
                .retrieve()
                .bodyToMono(ClientDto.class)
                .onErrorResume(WebClientResponseException.class, e -> {
                    return Mono.error(new ResourceNotFoundException(e.getResponseBodyAsString()));
                })
                .onErrorResume(e -> {
                    return Mono.error(new RuntimeException(e.getMessage()));
                });
    }
}
