package com.yakushevso.vacation_calc_neoflex.strategy;

import com.yakushevso.vacation_calc_neoflex.service.CalendarFetchService;
import com.yakushevso.vacation_calc_neoflex.service.DaysCalculatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
@Slf4j
public class DateRangeVacation implements VacationStrategy {
    private final CalendarFetchService calendarFetchService;
    private final DaysCalculatorService daysCalculatorService;

    @Override
    public double calculate(Double salary, Integer days, LocalDate startDate, LocalDate endDate) {
        log.info("Calculating vacation using DateRangeVacation strategy");

        Set<LocalDate> holidays = calendarFetchService.fetchHolidaysAndWeekends(startDate.getYear());

        int selectedDays = (int) IntStream
                .range(0, (int) ChronoUnit.DAYS.between(startDate, endDate) + 1)
                .mapToObj(startDate::plusDays)
                .filter(date -> !holidays.contains(date))
                .count();

        return daysCalculatorService.calculate(salary, selectedDays);
    }
}
