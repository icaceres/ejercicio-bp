package com.pichincha.account.service.mapper;

import com.pichincha.account.domain.Movement;
import com.pichincha.account.service.dto.MovementDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovementMapper {

    Movement toMovement(MovementDto movementDto);

    MovementDto toMovementDTO(Movement movement);
}
