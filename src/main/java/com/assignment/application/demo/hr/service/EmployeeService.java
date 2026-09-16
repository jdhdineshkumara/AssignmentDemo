package com.assignment.application.demo.hr.service;

import com.assignment.application.demo.common.exception.EmployeeNotFoundException;
import com.assignment.application.demo.hr.connector.UtilityConnector;
import com.assignment.application.demo.hr.controller.dto.EmployeeRS;
import com.assignment.application.demo.hr.domain.Employee;
import com.assignment.application.demo.hr.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final UtilityConnector utilityConnector;
    private final EmployeeRepository employeeRepository;

    public EmployeeRS getEmployee(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId).orElse(null);

        if (employee != null) {

            String departmentName = utilityConnector.getDepartmentNameByCode(employee.getDepartmentCode());
            return new EmployeeRS(employee.getEmpId(), employee.getName(), departmentName);

        } else {
            throw new EmployeeNotFoundException("Employee not found with the ID " + employeeId);
        }

    }
}
