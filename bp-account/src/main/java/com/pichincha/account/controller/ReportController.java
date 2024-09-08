package com.pichincha.account.controller;

import com.pichincha.account.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/reportes")
@AllArgsConstructor
public class ReportController {

    private ReportService reportService;

    @GetMapping
    public ResponseEntity<?> getReport(@RequestParam(value = "fechaInicio")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                       @RequestParam(value = "fechaFin")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate, @RequestParam(value = "identificacion") String identification) {
        return ResponseEntity.ok(reportService.getReport(startDate, endDate, identification));
    }
}
