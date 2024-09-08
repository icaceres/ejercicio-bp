package com.pichincha.client.service;

import com.pichincha.client.service.dto.ClientDto;

import java.util.List;

public interface ClientService {
    List<ClientDto> getAllClients();

    ClientDto getClientByIdentification(String identification);

    ClientDto saveClient(ClientDto client);

    ClientDto updateClient(String identification, ClientDto clientToUpdate);

    ClientDto deleteClient(String identification);
}
