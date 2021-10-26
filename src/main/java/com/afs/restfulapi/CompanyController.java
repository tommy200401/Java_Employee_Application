package com.afs.restfulapi;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyRepository companyRepository;

    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @GetMapping
    public List<Company> findAllCompanies() {
        return this.companyRepository.findAll();
    }
//
//    // /employees/{id}
//    @GetMapping("/{id}")
//    public Employee findById(@PathVariable Integer id) {
//        return this.employeeRepository.findById(id);
//    }
//
//    // /employees?gender=male
//    @GetMapping(params = "gender")
//    public List<Employee> findByGender(@RequestParam String gender) {
//        return this.employeeRepository.findByGender(gender);
//    }
//
//    // /employees?page=1&pageSize=5
//    @GetMapping(params = {"page", "size"})
//    public PageImpl<Employee> findByPageAndPageSize (@PageableDefault Pageable pageable) {
//        return this.employeeRepository.findPagingEmployees(pageable);
//    }
//
//    // post
//    @PostMapping
//    public Employee createEmployee(@RequestBody Employee employee){
//        return this.employeeRepository.createEmployee(employee);
//    }
//
//    // delete
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)  // Code=204
//    public void deleteById(@PathVariable Integer id){
//        this.employeeRepository.deleteById(id);
//    }
//
//    // Put
//    @PutMapping("/{id}")
//    public Employee editEmployee(@PathVariable Integer id, @RequestBody Employee updatedEmployee){
//        Employee originEmployee = this.employeeRepository.findById(id);
//        if (updatedEmployee.getAge() != null){
//            originEmployee.setAge(updatedEmployee.getAge());
//        }
//        if (updatedEmployee.getSalary()!=null){
//            originEmployee.setSalary(updatedEmployee.getSalary());
//        }
//        return this.employeeRepository.save(id, originEmployee);
//    }
}
