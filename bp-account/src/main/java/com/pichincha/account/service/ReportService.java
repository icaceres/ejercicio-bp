package com.pichincha.account.service;

import com.pichincha.account.domain.tuple.ReportTuple;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {
    List<ReportTuple> getReport(LocalDate startDate, LocalDate endDate, String identification);
}
