package com.assignment.application.demo.hr.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class EmployeeRS {

    private long employeeId;
    private String name;
    private String departmentName;
}
