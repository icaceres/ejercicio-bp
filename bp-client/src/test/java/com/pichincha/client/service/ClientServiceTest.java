package com.pichincha.client.service;

import com.pichincha.client.domain.Client;
import com.pichincha.client.exception.ResourceNotFoundException;
import com.pichincha.client.repository.ClientRepository;
import com.pichincha.client.service.dto.ClientDto;
import com.pichincha.client.service.impl.ClientServiceImpl;
import com.pichincha.client.service.mapper.ClientMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    public static final String TEST_CLIENT = "Test Client";
    public static final String NUMBER = "123456";
    public static final String NEW_CLIENT = "New Client";
    public static final String EDIT_CLIENT = "Edit Client";
    public static final String NUMBER_ONE = "1234567";
    @InjectMocks
    private ClientServiceImpl clientService;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientMapper clientMapper;


    @Test
    public void shouldBeGetAllClients() {
        Client client = new Client();
        client.setId(1L);
        client.setPersonName(TEST_CLIENT);
        client.setStatus(Boolean.TRUE);

        ClientDto clientDto = new ClientDto();
        clientDto.setId(1L);
        clientDto.setPersonName(TEST_CLIENT);
        clientDto.setStatus(Boolean.TRUE);

        when(clientRepository.findByStatus(Mockito.anyBoolean())).thenReturn(List.of(client));
        when(clientMapper.toClientDto(Mockito.any(Client.class))).thenReturn(clientDto);

        List<ClientDto> clients = clientService.getAllClients();

        assertNotNull(clients);
        assertEquals(1, clients.size());
        assertEquals(TEST_CLIENT, clients.get(0).getPersonName());
    }

    @Test
    public void shouldBeGetAllClientsNotFound() {
        when(clientRepository.findByStatus(Mockito.anyBoolean())).thenReturn(List.of());
        assertThrows(ResourceNotFoundException.class, () ->  clientService.getAllClients());
    }

    @Test
    public void shouldBeGetClientByIdentification() {
        Client client = new Client();
        client.setIdentification(NUMBER);
        client.setPersonName(TEST_CLIENT);
        client.setStatus(Boolean.TRUE);

        ClientDto clientDto = new ClientDto();
        clientDto.setIdentification(NUMBER);
        clientDto.setPersonName(TEST_CLIENT);
        clientDto.setStatus(Boolean.TRUE);

        when(clientRepository.findByIdentificationAndStatus(Mockito.anyString(), Mockito.anyBoolean())).thenReturn(Optional.of(client));
        when(clientMapper.toClientDto(Mockito.any(Client.class))).thenReturn(clientDto);

        ClientDto foundClientDTO = clientService.getClientByIdentification(NUMBER);

        assertNotNull(foundClientDTO);
        assertEquals(TEST_CLIENT, foundClientDTO.getPersonName());
    }

    @Test
    public void shouldBeGetClientByIdentificationNotFound() {
        when(clientRepository.findByIdentificationAndStatus(Mockito.anyString(), Mockito.anyBoolean())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> clientService.getClientByIdentification(NUMBER));
    }

    @Test
    public void shouldBeSaveClient() {
        ClientDto clientDto = new ClientDto();
        clientDto.setIdentification(NUMBER_ONE);
        clientDto.setPersonName(NEW_CLIENT);

        Client client = new Client();
        client.setIdentification(NUMBER_ONE);
        client.setPersonName(NEW_CLIENT);

        when(clientRepository.findByIdentificationAndStatus(Mockito.anyString(), Mockito.anyBoolean())).thenReturn(Optional.empty());
        when(clientMapper.toClient(Mockito.any(ClientDto.class))).thenReturn(client);
        when(clientMapper.toClientDto(Mockito.any(Client.class))).thenReturn(clientDto);
        when(clientRepository.save(Mockito.any(Client.class))).thenReturn(client);

        ClientDto savedClientDto = clientService.saveClient(clientDto);

        assertNotNull(savedClientDto);
        assertEquals(NEW_CLIENT, savedClientDto.getPersonName());
    }

    @Test
    public void shouldBeUpdateClient() {
        ClientDto clientDto = new ClientDto();
        clientDto.setIdentification(NUMBER);
        clientDto.setPersonName(EDIT_CLIENT);

        Client client = new Client();
        client.setIdentification(NUMBER);
        client.setPersonName(EDIT_CLIENT);

        when(clientRepository.findByIdentificationAndStatus(Mockito.anyString(), Mockito.anyBoolean())).thenReturn(Optional.of(client));
        when(clientMapper.toClient(Mockito.any(ClientDto.class))).thenReturn(client);
        when(clientMapper.toClientDto(Mockito.any(Client.class))).thenReturn(clientDto);
        when(clientRepository.save(Mockito.any(Client.class))).thenReturn(client);

        ClientDto updatedClientDto = clientService.updateClient(NUMBER, clientDto);

        assertNotNull(updatedClientDto);
        assertEquals(EDIT_CLIENT, updatedClientDto.getPersonName());
    }

    @Test
    public void shouldBeDeleteClient() {
        ClientDto clientDto = new ClientDto();
        clientDto.setIdentification(NUMBER_ONE);
        clientDto.setStatus(Boolean.FALSE);

        Client client = new Client();
        client.setIdentification(NUMBER_ONE);
        client.setStatus(Boolean.FALSE);

        when(clientRepository.findByIdentificationAndStatus(Mockito.anyString(), Mockito.anyBoolean())).thenReturn(Optional.of(client));
        when(clientMapper.toClient(Mockito.any(ClientDto.class))).thenReturn(client);
        when(clientMapper.toClientDto(Mockito.any(Client.class))).thenReturn(clientDto);
        when(clientRepository.save(Mockito.any(Client.class))).thenReturn(client);

        ClientDto deletedClientDto = clientService.deleteClient(NUMBER_ONE);

        assertNotNull(deletedClientDto);
        assertEquals(Boolean.FALSE, deletedClientDto.getStatus());
    }
}
