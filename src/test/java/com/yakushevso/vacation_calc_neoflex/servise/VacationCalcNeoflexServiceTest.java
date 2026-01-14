package com.yakushevso.vacation_calc_neoflex.servise;

import com.yakushevso.vacation_calc_neoflex.service.VacationCalcNeoflexService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VacationCalcNeoflexServiceTest {
    private final VacationCalcNeoflexService service = new VacationCalcNeoflexService();

    @Test
    void calculate_shouldReturnCorrectAmount() {
        double salary = 60000;
        int days = 14;

        double result = service.calculate(salary, days);

        assertEquals(28668.9, result, 0.1);
    }

    @Test
    void calculate_withZeroDays_shouldReturnZero() {
        double salary = 60000;
        int days = 0;

        double result = service.calculate(salary, days);

        assertEquals(0.0, result, 0.01);
    }

    @Test
    void calculate_withNegativeSalary_shouldReturnNegative() {
        double salary = -60000;
        int days = 10;

        double result = service.calculate(salary, days);

        assertEquals(-20477.8, result, 0.1);
    }
}
