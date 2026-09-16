package com.assignment.application.demo.common.exception;

public class DepartmentNotFoundException extends RuntimeException {

    public DepartmentNotFoundException(String message) {
        super("Department Not found ");
    }

}
