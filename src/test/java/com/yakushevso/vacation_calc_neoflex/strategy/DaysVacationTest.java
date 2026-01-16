package com.yakushevso.vacation_calc_neoflex.strategy;

import com.yakushevso.vacation_calc_neoflex.service.DaysCalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DaysVacationTest {
    private DaysVacation strategy;

    @BeforeEach
    void setUp() {
        strategy = new DaysVacation(new DaysCalculatorService());
    }

    @Test
    void calculate_shouldUseDaysStrategy() {
        double result = strategy.calculate(60000.0, 10, null, null);
        assertEquals(60000 / 29.3 * 10, result, 0.0001);
    }
}
