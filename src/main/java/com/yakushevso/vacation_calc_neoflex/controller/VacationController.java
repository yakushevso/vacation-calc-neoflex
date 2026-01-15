package com.yakushevso.vacation_calc_neoflex.controller;

import com.yakushevso.vacation_calc_neoflex.dto.VacationResponse;
import com.yakushevso.vacation_calc_neoflex.exception.InvalidDateRangeException;
import com.yakushevso.vacation_calc_neoflex.exception.InvalidDaysException;
import com.yakushevso.vacation_calc_neoflex.exception.InvalidSalaryException;
import com.yakushevso.vacation_calc_neoflex.service.DaysCalculatorService;
import com.yakushevso.vacation_calc_neoflex.strategy.VacationStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@Slf4j
public class VacationController {
    private final VacationStrategy daysVacation;
    private final VacationStrategy dateRangeVacation;
    private final DaysCalculatorService daysCalculatorService;

    @GetMapping("/calculate")
    public VacationResponse calculate(
            @RequestParam Double salary,
            @RequestParam(required = false) Integer days,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        log.info("call /calculate with: salary = {}, days = {}, startDate = {}, endDate = {}",
                salary, days, startDate, endDate);

        if (salary < 0) {
            throw new InvalidSalaryException();
        }

        VacationStrategy strategy;

        if (startDate != null && endDate != null) {
            if (endDate.isBefore(startDate)) {
                throw new InvalidDateRangeException();
            }
            strategy = dateRangeVacation;
        } else {
            if (days == null || days < 1) {
                throw new InvalidDaysException();
            }
            strategy = daysVacation;
        }

        double result = daysCalculatorService.roundToDecimal(
                strategy.calculate(salary, days, startDate, endDate));

        log.info("calculation result = {}", result);

        return new VacationResponse(result);
    }
}

// curl -s "localhost:8080/calculate?salary=60000&days=14" | jq
// curl -s "localhost:8080/calculate?salary=60000&startDate=2026-01-01&endDate=2026-01-15" | jq