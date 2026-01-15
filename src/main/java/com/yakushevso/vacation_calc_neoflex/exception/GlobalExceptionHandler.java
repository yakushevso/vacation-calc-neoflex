package com.yakushevso.vacation_calc_neoflex.exception;

import com.yakushevso.vacation_calc_neoflex.dto.VacationApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidDaysException.class)
    public ResponseEntity<VacationApiError> handleInvalidDays(InvalidDaysException ex) {
        log.error("Handled NegativeDaysException: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new VacationApiError(ex.getMessage()));
    }

    @ExceptionHandler(InvalidSalaryException.class)
    public ResponseEntity<VacationApiError> handleInvalidSalary(InvalidSalaryException ex) {
        log.error("Handled NegativeSalaryException: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new VacationApiError(ex.getMessage()));
    }

    @ExceptionHandler(InvalidDateRangeException.class)
    public ResponseEntity<VacationApiError> handleInvalidDateRange(InvalidDateRangeException ex) {
        log.error("Handle InvalidDateRangeException: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new VacationApiError(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<VacationApiError> handleInvalidData(MethodArgumentTypeMismatchException ex) {
        String msg = "Invalid date value";
        log.error("Handled InvalidDataException: {}", msg);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new VacationApiError(msg));
    }

    @ExceptionHandler(CalendarFetchException.class)
    public ResponseEntity<VacationApiError> handleInvalidData(CalendarFetchException ex) {
        log.error("Handled CalendarFetchException: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new VacationApiError(ex.getMessage()));
    }
}
