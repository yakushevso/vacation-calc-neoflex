package com.yakushevso.vacation_calc_neoflex.exception;

public class InvalidSalaryException extends RuntimeException {

    public InvalidSalaryException() {
        super("Invalid salary value");
    }
}
