package com.pichincha.account.domain.tuple;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ReportTuple {

    LocalDate getFecha();

    String getCliente();

    String geNumeroCuenta();

    String getTipoCuenta();

    String getTipoMovimiento();

    BigDecimal getSaldoInicial();

    BigDecimal getValorMovimiento();

    BigDecimal getSaldoFinal();
}
