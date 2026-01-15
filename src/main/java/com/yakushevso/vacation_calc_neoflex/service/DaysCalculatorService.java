package com.yakushevso.vacation_calc_neoflex.service;

import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

@Service
public class DaysCalculatorService {
    private static final double AVG_DAYS_IN_MONTH = 29.3;

    public double calculate(double salary, int days) {
        return salary / AVG_DAYS_IN_MONTH * days;
    }

    public double roundToDecimal(double value) {
        return Double.parseDouble(
                new DecimalFormat("#.##", DecimalFormatSymbols.getInstance(Locale.ENGLISH))
                .format(value));
    }
}
