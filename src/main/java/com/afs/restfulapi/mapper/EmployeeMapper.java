package com.afs.restfulapi.mapper;

import com.afs.restfulapi.dto.EmployeeRequest;
import com.afs.restfulapi.dto.EmployeeResponse;
import com.afs.restfulapi.entity.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public Employee toEntity(EmployeeRequest employeeRequest){
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeRequest, employee);
        return employee;
    }

    public EmployeeResponse toResponse(Employee employee){
        EmployeeResponse employeeResponse = new EmployeeResponse();
        BeanUtils.copyProperties(employeeResponse, employee);
        return employeeResponse;
    }
}
