package com.afs.restfulapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository){
        this.employeeRepository=employeeRepository;
    }
    @GetMapping
    public List<Employee> findAllEmployees(){
        return this.employeeRepository.findAll();
    }

    // /employees/{id}
    @GetMapping("/{id}")
    public Employee findById(@PathVariable Integer id){
        return this.employeeRepository.findById(id);
    }
}
