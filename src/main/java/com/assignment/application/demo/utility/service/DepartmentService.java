package com.assignment.application.demo.utility.service;

import org.springframework.stereotype.Service;

@Service
public interface DepartmentService {

    String getDepartmentNameByCode(String departmentCode);
}
