package com.pichincha.account.service.impl;

import com.pichincha.account.domain.Movement;
import com.pichincha.account.domain.enums.MovementTypeEnum;
import com.pichincha.account.exception.InvalidActionException;
import com.pichincha.account.exception.ResourceNotFoundException;
import com.pichincha.account.repository.AccountRepository;
import com.pichincha.account.repository.MovementRepository;
import com.pichincha.account.service.AccountService;
import com.pichincha.account.service.MovementService;
import com.pichincha.account.service.dto.AccountDto;
import com.pichincha.account.service.dto.MovementDto;
import com.pichincha.account.service.mapper.AccountMapper;
import com.pichincha.account.service.mapper.MovementMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class MovementServiceImpl implements MovementService {

    private MovementRepository movementRepository;

    private AccountRepository accountRepository;

    private AccountService accountService;

    private MovementMapper movementMapper;

    private AccountMapper accountMapper;

    @Override
    public List<MovementDto> getAllMovements() {
        List<MovementDto> movements = movementRepository.findAll().stream().map(movementMapper::toMovementDTO).toList();
        if (movements.isEmpty()) {
            throw new ResourceNotFoundException("Movimientos no encontrado.");
        }
        return movements;
    }

    @Override
    public MovementDto getMovementById(Long id) {
        return movementRepository.findById(id).map(movementMapper::toMovementDTO).orElseThrow(() ->
                new ResourceNotFoundException("Movimiento no encontrado."));
    }

    @Transactional
    @Override
    public MovementDto saveMovement(MovementDto movement) {
        AccountDto account = accountService.getAccountByNumber(movement.getAccount().getAccountNumber());
        BigDecimal newBalance = account.getInitialBalance().add(movement.getAmount());
        validateBalance(newBalance);

        movement.setDate(LocalDate.now());
        movement.setMovementType(getMovementType(movement.getAmount()));
        movement.setInitialBalance(account.getInitialBalance());
        movement.setFinalBalance(newBalance);

        account.setInitialBalance(newBalance);
        accountRepository.save(accountMapper.toAccount(account));

        Movement movementToPersist = movementMapper.toMovement(movement);
        movementToPersist.setAccount(accountMapper.toAccount(account));

        return movementMapper.toMovementDTO(movementRepository.save(movementToPersist));
    }

    private void validateBalance(BigDecimal newBalance) {
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidActionException("Saldo no disponible");
        }
    }

    private String getMovementType(BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) > 0 ? MovementTypeEnum.DEPOSITO.name() : MovementTypeEnum.RETIRO.name();
    }
}
