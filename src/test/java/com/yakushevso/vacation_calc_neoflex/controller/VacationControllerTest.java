package com.yakushevso.vacation_calc_neoflex.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class VacationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void calculateByDays_success() throws Exception {
        mockMvc.perform(get("/calculate")
                        .param("salary", "60000")
                        .param("days", "14"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount").value(28668.94));
    }

    @Test
    void calculateByDateRange_success() throws Exception {
        mockMvc.perform(get("/calculate")
                        .param("salary", "60000")
                        .param("startDate", "2026-01-01")
                        .param("endDate", "2026-01-15"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount").value(8191.13));
    }

    @Test
    void negativeSalary_badRequest() throws Exception {
        mockMvc.perform(get("/calculate")
                        .param("salary", "-60000")
                        .param("days", "10"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid salary value"));
    }

    @Test
    void invalidDays_badRequest() throws Exception {
        mockMvc.perform(get("/calculate")
                        .param("salary", "60000")
                        .param("days", "-14"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid days value"));
    }

    @Test
    void invalidDateRange_badRequest() throws Exception {
        mockMvc.perform(get("/calculate")
                        .param("salary", "60000")
                        .param("startDate", "2027-01-01")
                        .param("endDate", "2026-01-15"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid date range"));
    }

    @Test
    void invalidDateFormat_badRequest() throws Exception {
        mockMvc.perform(get("/calculate")
                        .param("salary", "60000")
                        .param("startDate", "01-01-2026")
                        .param("endDate", "2026-01-15"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid date value"));
    }
}
