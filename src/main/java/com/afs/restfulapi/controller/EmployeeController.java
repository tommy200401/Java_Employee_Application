package com.afs.restfulapi.controller;

import com.afs.restfulapi.dto.EmployeeRequest;
import com.afs.restfulapi.dto.EmployeeResponse;
import com.afs.restfulapi.entity.Employee;
import com.afs.restfulapi.mapper.EmployeeMapper;
import com.afs.restfulapi.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private EmployeeMapper employeeMapper;

    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    //Todo: change all employeeRepository to employeeService upon update

    @GetMapping
    public List<EmployeeResponse> findAllEmployees() {

        return this.employeeService.findAll().stream().map(employee->employeeMapper.toResponse(employee)).collect(Collectors.toList());
    }

    // /employees/{id}
    @GetMapping("/{id}")
    public EmployeeResponse findById(@PathVariable Integer id) {

        return this.employeeMapper.toResponse(employeeService.findById(id));
    }

//todo: change all to mappers/response
    // /employees?gender=male
    @GetMapping(params = "gender")
    public List<EmployeeResponse> findByGender(@RequestParam String gender) {
        return this.employeeService.findByGender(gender).stream().map(employee->employeeMapper.toResponse(employee)).collect(Collectors.toList());
    }

    // /employees?page=1&pageSize=5
    @GetMapping(params = {"page", "pageSize"})
    public Page<EmployeeResponse> findByPageAndPageSize(@PageableDefault Pageable pageable) {
        return employeeService.findPagingEmployees(pageable).map(employee->employeeMapper.toResponse(employee));
    }

    // post
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)  // Code=201
    public Employee createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return this.employeeService.createEmployee(employeeMapper.toEntity(employeeRequest));
    }

    // delete
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)  // Code=204
    public void deleteById(@PathVariable Integer id) {
        this.employeeService.deleteEmployee(id);
    }

    //todo: change to update, not edit
    //todo: delete logic
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
