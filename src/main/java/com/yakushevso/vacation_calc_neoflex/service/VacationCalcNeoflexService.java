package com.yakushevso.vacation_calc_neoflex.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class VacationCalcNeoflexService {
    private static final double AVG_DAYS_IN_MONTH = 29.3;

    public double calculate(double salary, int days) {
        double result = salary / AVG_DAYS_IN_MONTH * days;
        log.info("calculation result = {}", result);

        return result;
    }
}
