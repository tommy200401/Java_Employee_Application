package com.afs.restfulapi;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {
    private final List<Employee> employees = new ArrayList<>();

    public EmployeeRepository() {
        employees.add(new Employee(1, "Ang", 18, "male", 99999));
    }

    public List<Employee> findAll(){
        return employees;
    }

    public Employee findById(Integer id){
        return this.employees.stream().filter(item->id.equals(item.getId())).findFirst().orElseThrow(EmployeeNotFoundException::new);
    }
}
