package com.pichincha.account.controller;

import com.pichincha.account.service.MovementService;
import com.pichincha.account.service.dto.MovementDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/movimientos")
@AllArgsConstructor
public class MovementController {

    private MovementService movementService;

    @GetMapping
    public ResponseEntity<?> getAllMovements() {
        return ResponseEntity.ok(movementService.getAllMovements());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovementDto> getMovementById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(movementService.getMovementById(id));
    }

    @PostMapping
    public ResponseEntity<?> createMovement(@RequestBody MovementDto movement) {
        return new ResponseEntity<>(movementService.saveMovement(movement), HttpStatus.CREATED);

    }
}
