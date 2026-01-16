package com.yakushevso.vacation_calc_neoflex.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yakushevso.vacation_calc_neoflex.exception.CalendarFetchException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class CalendarFetchService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public Set<LocalDate> fetchHolidaysAndWeekends(int year) {
        Set<LocalDate> holidaysAndWeekends = new LinkedHashSet<>();
        String calendarUrl = "https://xmlcalendar.ru/data/ru/" + year + "/calendar.json";

        try {
            String json = restTemplate.getForObject(calendarUrl, String.class);
            JsonNode root = objectMapper.readTree(json);
            JsonNode months = root.get("months");

            for (JsonNode monthNode : months) {
                int month = monthNode.get("month").asInt();
                String days = monthNode.get("days").asText();

                for (String dayToken : days.split(",")) {
                    String cleanDay = dayToken.replaceAll("[^0-9]", "");
                    int day = Integer.parseInt(cleanDay);
                    holidaysAndWeekends.add(LocalDate.of(year, month, day));
                }
            }
        } catch (Exception e) {
            throw new CalendarFetchException();
        }

        return holidaysAndWeekends;
    }
}
