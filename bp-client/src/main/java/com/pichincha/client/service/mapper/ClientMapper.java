package com.pichincha.client.service.mapper;

import com.pichincha.client.domain.Client;
import com.pichincha.client.service.dto.ClientDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client toClient(ClientDto clientDto);

    ClientDto toClientDto (Client client);
}
