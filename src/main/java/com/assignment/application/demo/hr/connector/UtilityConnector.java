package com.assignment.application.demo.hr.connector;

import com.assignment.application.demo.utility.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UtilityConnector {

    private final DepartmentService departmentService;

    public String getDepartmentNameByCode(String departmentCode) {

        return departmentService.getDepartmentNameByCode(departmentCode);
    }


}
