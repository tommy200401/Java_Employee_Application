package com.afs.restfulapi;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public List<Employee> findAllEmployees() {
        return this.employeeRepository.findAll();
    }

    // /employees/{id}
    @GetMapping("/{id}")
    public Employee findById(@PathVariable Integer id) {
        return this.employeeRepository.findById(id);
    }

    // /employees?gender=male
    @GetMapping(params = "gender")
    public List<Employee> findByGender(@RequestParam String gender) {
        return this.employeeRepository.findByGender(gender);
    }

    // todo: 2 parameters

    // post
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee){
        return this.employeeRepository.createEmployee(employee);
    }

    // delete
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)  // Code=204
    public void deleteById(@PathVariable Integer id){
        this.employeeRepository.deleteById(id);
    }

    // Put
    @PutMapping("/{id}")
    public Employee editEmployee(@PathVariable Integer id, @RequestBody Employee updatedEmployee){
        Employee originEmployee = this.employeeRepository.findById(id);
        if (updatedEmployee.getAge() != null){
            originEmployee.setAge(updatedEmployee.getAge());
        }
        if (updatedEmployee.getSalary()!=null){
            originEmployee.setSalary(updatedEmployee.getSalary());
        }
        return this.employeeRepository.save(id, originEmployee);
    }
}
