package com.afs.restfulapi.service;

import com.afs.restfulapi.entity.Employee;
import com.afs.restfulapi.exception.EmployeeNotFoundException;
import com.afs.restfulapi.repository.EmployeeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll() {
        return this.employeeRepository.findAll();
    }

    public Employee findById(Integer id) {
        return this.employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> findPagingEmployees(int page, int pageSize) {
        return this.employeeRepository.findAll(PageRequest.of(page, pageSize)).getContent();
    }

    public List<Employee> findByGender(String gender) {
        return employeeRepository.findAllByGender(gender);
    }

    public Employee editEmployee(Integer id, Employee updatedEmployee) {
        Employee originEmployee = employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
        if (updatedEmployee.getAge() != null) {
            originEmployee.setAge(updatedEmployee.getAge());
        }
        if (updatedEmployee.getSalary() != null) {
            originEmployee.setSalary(updatedEmployee.getSalary());
        }
        return this.employeeRepository.save(originEmployee);
    }

    public Employee createEmployee(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    public void deleteEmployee(Integer id){
        // Create new employee first, in case non-exist ID, findById will return exception
        Employee employee = this.findById(id);
        this.employeeRepository.delete(employee);
    }

    public void deleteAllEmployee() {
        this.employeeRepository.deleteAll();
    }
}
