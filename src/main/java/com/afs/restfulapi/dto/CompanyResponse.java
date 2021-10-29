package com.afs.restfulapi.dto;

import com.afs.restfulapi.entity.Employee;

import java.util.List;

public class CompanyResponse {
    private Integer id;
    private String name;
    private List<EmployeeResponse> employees;

    public CompanyResponse () {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EmployeeResponse> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeResponse> employees) {
        this.employees = employees;
    }
}
