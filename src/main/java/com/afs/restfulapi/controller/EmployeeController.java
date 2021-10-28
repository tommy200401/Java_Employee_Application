package com.afs.restfulapi.controller;

import com.afs.restfulapi.entity.Employee;
import com.afs.restfulapi.repository.EmployeeRepository;
import com.afs.restfulapi.repository.NewEmployeeRepository;
import com.afs.restfulapi.service.EmployeeService;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //Todo: change all employeeRepository to employeeService upon update

    @GetMapping
    public List<Employee> findAllEmployees() {
        return this.employeeService.findAll();
    }

    // /employees/{id}
    @GetMapping("/{id}")
    public Employee findById(@PathVariable Integer id) {
        return this.employeeService.findById(id);
    }

    // /employees?gender=male
    @GetMapping(params = "gender")
    public List<Employee> findByGender(@RequestParam String gender) {
        return this.employeeService.findByGender(gender);
    }

    // /employees?page=1&pageSize=5
    @GetMapping(params = {"page", "pageSize"})
    public PageImpl<Employee> findByPageAndPageSize(@PageableDefault Pageable pageable) {
        return this.employeeService.findPagingEmployees(pageable);
    }

    // post
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)  // Code=201
    public Employee createEmployee(@RequestBody Employee employee) {
        return this.employeeService.createEmployee(employee);
    }

    // delete
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)  // Code=204
    public void deleteById(@PathVariable Integer id) {
        this.employeeService.deleteEmployee(id);
    }

    // Put
    @PutMapping("/{id}")
    public Employee editEmployee(@PathVariable Integer id, @RequestBody Employee updatedEmployee) {
        Employee originEmployee = this.employeeService.findById(id);
        if (updatedEmployee.getAge() != null) {
            originEmployee.setAge(updatedEmployee.getAge());
        }
        if (updatedEmployee.getSalary() != null) {
            originEmployee.setSalary(updatedEmployee.getSalary());
        }
        return this.employeeService.editEmployee(id, originEmployee);
    }
}
