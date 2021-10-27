package com.afs.restfulapi;

import org.springframework.stereotype.Service;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

//@Service
public class EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }

    public List<Employee> findAll() {
        return repo.findAll();
    }

    public Employee findById(Integer id) {
        return repo.findById(id);
    }

    public List<Employee> findByGender(String gender) {
        return repo.findByGender(gender);
    }
}
