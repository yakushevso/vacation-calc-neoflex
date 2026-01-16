package com.yakushevso.vacation_calc_neoflex.strategy;

import com.yakushevso.vacation_calc_neoflex.service.CalendarFetchService;
import com.yakushevso.vacation_calc_neoflex.service.DaysCalculatorService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static java.util.Set.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DateRangeVacationTest {

    @Test
    void calculate_shouldNotThrowException() {
        CalendarFetchService calendarFetchService = mock(CalendarFetchService.class);
        DaysCalculatorService daysCalculatorService = new DaysCalculatorService();

        when(calendarFetchService.fetchHolidaysAndWeekends(2026))
                .thenReturn(of(
                        LocalDate.of(2026, 1, 10),
                        LocalDate.of(2026, 1, 11)
                ));

        DateRangeVacation strategy = new DateRangeVacation(calendarFetchService, daysCalculatorService);

        double result = strategy.calculate(
                60000.0,
                null,
                LocalDate.of(2026, 1, 10),
                LocalDate.of(2026, 1, 15)
        );

        assertEquals(60000 / 29.3 * 4, result, 0.01);
    }
}