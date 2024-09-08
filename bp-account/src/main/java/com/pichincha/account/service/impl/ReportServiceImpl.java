package com.pichincha.account.service.impl;

import com.pichincha.account.domain.tuple.ReportTuple;
import com.pichincha.account.exception.ResourceNotFoundException;
import com.pichincha.account.repository.MovementRepository;
import com.pichincha.account.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {

    private MovementRepository movementRepository;

    @Override
    public List<ReportTuple> getReport(LocalDate startDate, LocalDate endDate, String identification) {
        List<ReportTuple> report = movementRepository.getReportByDatesAndIdentification(startDate, endDate, identification);
        if (report.isEmpty()) {
            throw new ResourceNotFoundException("No existen registros para con esos parámetros.");
        }
        return report;
    }
}
