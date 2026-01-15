package com.yakushevso.vacation_calc_neoflex.exception;

public class InvalidDaysException extends RuntimeException {

    public InvalidDaysException() {
        super("Invalid days value");
    }
}
