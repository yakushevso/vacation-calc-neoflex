package com.yakushevso.vacation_calc_neoflex.strategy;

import com.yakushevso.vacation_calc_neoflex.service.DaysCalculatorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@AllArgsConstructor
@Slf4j
public class DaysVacation implements VacationStrategy {
    private final DaysCalculatorService daysCalculatorService;

    @Override
    public double calculate(Double salary, Integer days, LocalDate startDate, LocalDate endDate) {
        log.info("Calculating vacation using DaysVacation strategy");

        return daysCalculatorService.calculate(salary, days);
    }
}
