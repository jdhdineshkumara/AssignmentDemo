package com.assignment.application.demo.common.exception;

import com.assignment.application.demo.hr.controller.dto.ErrorRS;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public static final String DEPARTMENT_NOT_FOUND = "DEPARTMENT_NOT_FOUND";
    public static final String EMPLOYEE_NOT_FOUND = "EMPLOYEE_NOT_FOUND";
    public static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
    public static final String UTILITY_NOT_FOUND = "UTILITY_NOT_FOUND";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorRS> handleUnexpectedException(
            Exception ex) {

        return buildResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                INTERNAL_SERVER_ERROR,
                "An unexpected error occurred"
        );
    }

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<ErrorRS> handleDepartmentNotFoundException(
            DepartmentNotFoundException ex) {

        return buildResponse(
                HttpStatus.NOT_FOUND,
                DEPARTMENT_NOT_FOUND,
                ex.getMessage()
        );
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorRS> handleEmployeeNotFoundException(
            EmployeeNotFoundException ex) {

        return buildResponse(
                HttpStatus.NOT_FOUND,
                EMPLOYEE_NOT_FOUND,
                ex.getMessage()
        );
    }

    @ExceptionHandler(UtilityNotFoundException.class)
    public ResponseEntity<ErrorRS> handleUtilityNotFoundException(
            UtilityNotFoundException ex) {

        return buildResponse(
                HttpStatus.NOT_FOUND,
                UTILITY_NOT_FOUND,
                ex.getMessage()
        );
    }


    private ResponseEntity<ErrorRS> buildResponse(HttpStatus status, String error, String message) {

        ErrorRS response = new ErrorRS(
                Instant.now(),
                status.value(),
                error,
                message
        );

        return ResponseEntity.status(status).body(response);
    }
}
