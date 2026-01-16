package com.yakushevso.vacation_calc_neoflex.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DaysCalculatorServiceTest {
    private DaysCalculatorService service;

    @BeforeEach
    void setUp() {
        service = new DaysCalculatorService();
    }

    @Test
    void calculate_shouldReturnCorrectValue() {
        double result = service.calculate(60000, 14);
        assertEquals(60000 / 29.3 * 14, result, 0.0001);
    }

    @Test
    void roundToDecimal_shouldRoundToTwoDigits() {
        double result = service.roundToDecimal(123.4567);
        assertEquals(123.46, result);
    }
}
