package com.afs.restfulapi.service;

import com.afs.restfulapi.entity.Employee;
import com.afs.restfulapi.exception.EmployeeNotFoundException;
import com.afs.restfulapi.repository.EmployeeRepository;
import com.afs.restfulapi.repository.NewEmployeeRepository;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

@Service
public class EmployeeService {

    private NewEmployeeRepository newEmployeeRepository;

    public EmployeeService(NewEmployeeRepository newEmployeeRepository) {
        this.newEmployeeRepository = newEmployeeRepository;
    }

    public List<Employee> findAll() {
        return this.newEmployeeRepository.findAll();
    }

    public Employee findById(Integer id) {
        return this.newEmployeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
    }

    public PageImpl<Employee> findPagingEmployees(Pageable pageable) {
        return (PageImpl<Employee>) this.newEmployeeRepository.findAll(pageable);
    }

    public List<Employee> findByGender(String gender) {
        return newEmployeeRepository.findAllByGender(gender);
    }
//
//    public Employee updateEmployee(Integer id, Employee updatedEmployee){
//        return this.newEmployeeRepository.save(id, updatedEmployee);
//    }

    public Employee editEmployee(Integer id, Employee updatedEmployee) {
        Employee originEmployee = newEmployeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
        if (updatedEmployee.getAge() != null) {
            originEmployee.setAge(updatedEmployee.getAge());
        }
        if (updatedEmployee.getSalary() != null) {
            originEmployee.setSalary(updatedEmployee.getSalary());
        }
        return this.newEmployeeRepository.save(originEmployee);
    }

    public Employee createEmployee(Employee employee) {
        return this.newEmployeeRepository.save(employee);
    }

    public void deleteEmployee(Integer id){
        // Create new employee first, in case non-exist ID, findById will return exception
        Employee employee = this.findById(id);
        this.newEmployeeRepository.delete(employee);}
}
