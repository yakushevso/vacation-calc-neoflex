package com.yakushevso.vacation_calc_neoflex.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yakushevso.vacation_calc_neoflex.exception.CalendarFetchException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CalendarFetchServiceTest {
    private RestTemplate restTemplate;
    private CalendarFetchService service;

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        service = new CalendarFetchService(restTemplate, new ObjectMapper());
    }

    @Test
    void fetchHolidaysAndWeekends_success() {
        String fakeJson = "{\"months\": [{ \"month\": 1, \"days\": \"1,2,3\" },{ \"month\": 2, \"days\": \"15,16\" }]}";

        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(fakeJson);

        Set<LocalDate> holidays = service.fetchHolidaysAndWeekends(2026);

        assertEquals(5, holidays.size());
        assertTrue(holidays.contains(LocalDate.of(2026, 1, 1)));
        assertTrue(holidays.contains(LocalDate.of(2026, 1, 2)));
        assertTrue(holidays.contains(LocalDate.of(2026, 1, 3)));
        assertTrue(holidays.contains(LocalDate.of(2026, 2, 15)));
        assertTrue(holidays.contains(LocalDate.of(2026, 2, 16)));
    }

    @Test
    void fetchHolidaysAndWeekends_badRequest() {
        when(restTemplate.getForObject(anyString(), eq(String.class)))
                .thenThrow(new RuntimeException("Failed to fetch holidays and weekends"));

        assertThrows(CalendarFetchException.class,
                () -> service.fetchHolidaysAndWeekends(2026));
    }
}
