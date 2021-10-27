package com.afs.restfulapi.service;

import com.afs.restfulapi.entity.Employee;
import com.afs.restfulapi.repository.EmployeeRepository;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

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

//    public Employee editEmployee(Integer id, Employee updatedEmployee) {
//        Employee originEmployee = repo.findById(id);
//        if (updatedEmployee.getAge() != null) {
//            originEmployee.setAge(updatedEmployee.getAge());
//        }
//        if (updatedEmployee.getSalary() != null) {
//            originEmployee.setSalary(updatedEmployee.getSalary());
//        }
//        return repo.updateEmployee(id, originEmployee);
//    }

    public Employee updateEmployee(Integer id, Employee updatedEmployee){
        return repo.updateEmployee(id, updatedEmployee);
    }

    public Employee createEmployee(Employee employee) {
        return repo.createEmployee(employee);
    }

    public Employee deleteEmployee(Integer id){ return null;}

    public PageImpl<Employee> findPagingEmployees(Pageable pageable) {
        return repo.findPagingEmployees(pageable);
    }
}