package com.yakushevso.vacation_calc_neoflex.controller;

import com.yakushevso.vacation_calc_neoflex.model.VacationCalcNeoflexResponse;
import com.yakushevso.vacation_calc_neoflex.service.VacationCalcNeoflexService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class VacationCalcNeoflexController {
    private final VacationCalcNeoflexService service;

    @GetMapping("/calculate")
    public VacationCalcNeoflexResponse calculate(
            @RequestParam double salary,
            @RequestParam int days) {

        log.info("call /calculate with salary = {}, days = {}", salary, days);
        double result = service.calculate(salary, days);

        return new VacationCalcNeoflexResponse(result);
    }
}

// localhost:8080/calculate?salary=60000&days=14