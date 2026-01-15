package com.yakushevso.vacation_calc_neoflex.exception;

public class InvalidDateRangeException extends RuntimeException{

    public InvalidDateRangeException() {
        super("Invalid date range");
    }
}
