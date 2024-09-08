package com.pichincha.account.service;

import com.pichincha.account.service.dto.MovementDto;

import java.util.List;

public interface MovementService {
    List<MovementDto> getAllMovements();

    MovementDto getMovementById(Long id);

    MovementDto saveMovement(MovementDto movement);
}
