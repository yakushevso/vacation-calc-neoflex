package com.yakushevso.vacation_calc_neoflex.strategy;

import java.time.LocalDate;

public interface VacationStrategy {
    double calculate(Double salary, Integer days, LocalDate startDate, LocalDate endDate);
}
