package com.pichincha.account.repository;

import com.pichincha.account.domain.Movement;
import com.pichincha.account.domain.tuple.ReportTuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface MovementRepository extends JpaRepository<Movement, Long> {

    @Query(value = "SELECT m.date as fecha, p.person_name as cliente, a.account_number as numeroCuenta," +
            " a.account_type as tipoCuenta, m.movement_type as tipoMovimiento," +
            " m.initial_balance as saldoInicial, m.amount as valorMovimiento, m.final_balance as saldoFinal" +
            " FROM movement m" +
            " LEFT JOIN account a ON m.account_id = a.id" +
            " LEFT JOIN person p ON a.client_id = p.id" +
            " WHERE m.date BETWEEN ?1 AND ?2" +
            " AND p.identification = ?3", nativeQuery = true)
    List<ReportTuple> getReportByDatesAndIdentification(LocalDate startDate, LocalDate endDate, String identification);

}
