package com.pichincha.client.service.impl;

import com.pichincha.client.exception.InvalidActionException;
import com.pichincha.client.exception.ResourceNotFoundException;
import com.pichincha.client.repository.ClientRepository;
import com.pichincha.client.service.ClientService;
import com.pichincha.client.service.dto.ClientDto;
import com.pichincha.client.service.mapper.ClientMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;
    private ClientMapper clientMapper;

    @Override
    public List<ClientDto> getAllClients() {
        List<ClientDto> clients = clientRepository.findByStatus(Boolean.TRUE).stream().map(clientMapper::toClientDto).toList();
        if (clients.isEmpty()) {
            throw new ResourceNotFoundException("Clientes no encontrados.");
        }
        return clients;
    }

    @Override
    public ClientDto getClientByIdentification(String identification) {
        return clientRepository.findByIdentificationAndStatus(identification, Boolean.TRUE).map(clientMapper::toClientDto).orElseThrow(()
                -> new ResourceNotFoundException("Cliente no encontrado."));
    }

    @Override
    public ClientDto saveClient(ClientDto client) {
        clientRepository.findByIdentificationAndStatus(client.getIdentification(), Boolean.TRUE).ifPresent(res -> {
            throw new InvalidActionException("El cliente con número de identificación " + client.getIdentification() + " ya se encuentra registrado.");
        });
        return clientMapper.toClientDto(clientRepository.save(clientMapper.toClient(client)));
    }

    @Override
    public ClientDto updateClient(String identification, ClientDto clientToUpdate) {
        ClientDto client = getClientByIdentification(identification);
        client.setPersonName(clientToUpdate.getPersonName());
        client.setAddress(clientToUpdate.getAddress());
        client.setTelephone(clientToUpdate.getTelephone());
        client.setPassword(clientToUpdate.getPassword());
        return clientMapper.toClientDto(clientRepository.save(clientMapper.toClient(client)));
    }

    @Override
    public ClientDto deleteClient(String identification) {
        ClientDto client = getClientByIdentification(identification);
        client.setStatus(Boolean.FALSE);
        return clientMapper.toClientDto(clientRepository.save(clientMapper.toClient(client)));
    }
}
