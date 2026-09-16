package com.assignment.application.demo.hr.controller;

import com.assignment.application.demo.hr.controller.dto.EmployeeRS;
import com.assignment.application.demo.hr.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(EmployeeController.PATH)
public class EmployeeController {

    public static final String PATH = "/api/employees";
    public static final String EMPLOYEE_ID = "/{employeeId}";

    private final EmployeeService employeeService;

    @GetMapping(EMPLOYEE_ID)
    public EmployeeRS getEmployee(@PathVariable Long employeeId) {
        return employeeService.getEmployee(employeeId);
    }
}
