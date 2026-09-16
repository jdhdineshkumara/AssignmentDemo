package com.assignment.application.demo.utility.service;

import com.assignment.application.demo.common.exception.DepartmentNotFoundException;
import com.assignment.application.demo.common.exception.UtilityNotFoundException;
import com.assignment.application.demo.utility.domain.Department;
import com.assignment.application.demo.utility.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{

    private final DepartmentRepository departmentRepository;

    @Override
    public String getDepartmentNameByCode(String departmentCode) {

        try {

            Department department = departmentRepository
                    .findByDepartmentCode(departmentCode)
                    .orElseThrow(() ->
                            new DepartmentNotFoundException(departmentCode));

            return department.getName();

        } catch (DepartmentNotFoundException ex) {
            throw ex;
        } catch (DataAccessException ex) {
            throw new UtilityNotFoundException("Couldn't retrieve department ", ex);
        }
    }
}
