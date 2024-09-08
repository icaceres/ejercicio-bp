package com.pichincha.account.service;

import com.pichincha.account.domain.Account;
import com.pichincha.account.domain.Movement;
import com.pichincha.account.exception.InvalidActionException;
import com.pichincha.account.exception.ResourceNotFoundException;
import com.pichincha.account.repository.AccountRepository;
import com.pichincha.account.repository.MovementRepository;
import com.pichincha.account.service.dto.AccountDto;
import com.pichincha.account.service.dto.MovementDto;
import com.pichincha.account.service.impl.MovementServiceImpl;
import com.pichincha.account.service.mapper.AccountMapper;
import com.pichincha.account.service.mapper.MovementMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class MovementServiceTest {

    @InjectMocks
    private MovementServiceImpl movementService;

    @Mock
    private MovementRepository movementRepository;

    @Mock
    private AccountService accountService;

    @Mock
    private MovementMapper movementMapper;

    @Mock
    private AccountMapper accountMapper;

    @Mock
    private AccountRepository accountRepository;

    @Test
    public void shouldBeFindMovementByIdSuccess() {
        Movement movement = new Movement();
        movement.setId(1L);
        movement.setAmount(BigDecimal.valueOf(500.00));

        MovementDto movementDto = new MovementDto();
        movementDto.setId(1L);
        movementDto.setAmount(BigDecimal.valueOf(500.00));

        when(movementRepository.findById(1L)).thenReturn(Optional.of(movement));
        when(movementMapper.toMovementDTO(movement)).thenReturn(movementDto);
        MovementDto foundMovement = movementService.getMovementById(1L);

        assertEquals(500.00, foundMovement.getAmount().doubleValue());
    }

    @Test
    public void testFindMovementByIdNotFound() {
        when(movementRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> movementService.getMovementById(1L));
    }

    @Test
    public void shouldBeSaveMovementThrowsInsufficientBalanceException() {

        MovementDto movementDto = new MovementDto();
        movementDto.setAmount(BigDecimal.valueOf(-1500.00));
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountNumber("123456");
        accountDto.setInitialBalance(BigDecimal.valueOf(500));
        movementDto.setAccount(accountDto);

        Movement newMovement = new Movement();
        newMovement.setAmount(BigDecimal.valueOf(-1500.00));

        when(accountService.getAccountByNumber(accountDto.getAccountNumber())).thenReturn(accountDto);

        assertThrows(InvalidActionException.class, () -> movementService.saveMovement(movementDto)) ;
    }

    @Test
    public void testSaveMovement() {

        MovementDto movementDto = new MovementDto();
        movementDto.setAmount(BigDecimal.valueOf(1500.00));
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountNumber("123456");
        accountDto.setInitialBalance(BigDecimal.valueOf(500));
        movementDto.setAccount(accountDto);

        Movement newMovement = new Movement();
        newMovement.setAmount(BigDecimal.valueOf(1500.00));

        when(accountService.getAccountByNumber(accountDto.getAccountNumber())).thenReturn(accountDto);
        when(accountMapper.toAccount(Mockito.any(AccountDto.class))).thenReturn(new Account());
        when(accountRepository.save(Mockito.any(Account.class))).thenReturn(new Account());
        when(movementMapper.toMovement(Mockito.any(MovementDto.class))).thenReturn(newMovement);

        when(movementRepository.save(Mockito.any(Movement.class))).thenReturn(newMovement);
        when(movementMapper.toMovementDTO(Mockito.any(Movement.class))).thenReturn(movementDto);

        MovementDto returnedMovementDto = movementService.saveMovement(movementDto);

        assertEquals(1500.00, returnedMovementDto.getAmount().doubleValue());
    }

}
