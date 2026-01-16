package com.yakushevso.vacation_calc_neoflex.exception;

public class CalendarFetchException extends RuntimeException {

    public CalendarFetchException() {
        super("Failed to fetch holidays and weekends");
    }
}
