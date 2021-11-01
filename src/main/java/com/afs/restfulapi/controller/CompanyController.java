package com.afs.restfulapi.controller;

import com.afs.restfulapi.dto.CompanyRequest;
import com.afs.restfulapi.dto.CompanyResponse;
import com.afs.restfulapi.dto.EmployeeResponse;
import com.afs.restfulapi.entity.Company;
import com.afs.restfulapi.mapper.CompanyMapper;
import com.afs.restfulapi.mapper.EmployeeMapper;
import com.afs.restfulapi.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private CompanyService companyService;
    private CompanyMapper companyMapper;
    private EmployeeMapper employeeMapper;

    public CompanyController(CompanyService companyService, CompanyMapper companyMapper, EmployeeMapper employeeMapper) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping
    public List<CompanyResponse> findAllCompanies() {
        return this.companyService.findAll().stream().map(companyMapper::toResponse).collect(Collectors.toList());
    }

    // /companies/{id}
    @GetMapping("/{id}")
    public CompanyResponse findById(@PathVariable Integer id) {
        return this.companyMapper.toResponse(companyService.findById(id));
    }

    // Todo: add method
    // /companies/{id}/employees
    @GetMapping("/{id}/employees")
    public List<EmployeeResponse> getEmployeeListInCompanyById(@PathVariable Integer id) {
        return this.companyService.getEmployeeListInCompanyById(id)
                .stream()
                .map(employeeMapper::toResponse)
                .collect(Collectors.toList());
    }

    // /companies?page=1&pageSize=5
    @GetMapping(params = {"page", "pageSize"})
    public List<CompanyResponse> findByPageAndPageSize(@RequestParam Integer page,
                                                        @RequestParam Integer pageSize) {
        return companyService.findPagingCompanies(page, pageSize)
                .stream()
                .map(companyMapper::toResponse)
                .collect(Collectors.toList());
    }

    //todo: change company to companyResponse
    // post
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)  // Code=201
    public CompanyResponse createCompany(@RequestBody CompanyRequest companyRequest) {
        return companyMapper.toResponse(this.companyService.createCompany(companyMapper.toEntity(companyRequest)));
    }

    // delete
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)  // Code=204
    public void deleteById(@PathVariable Integer id) {
        this.companyService.deleteCompany(id);
    }

    // Put
    //todo: change to update, not edit
    // Put
    @PutMapping("/{id}")
    public CompanyResponse editCompany(@PathVariable Integer id,
                          @RequestBody CompanyRequest companyRequest) {
        return companyMapper.toResponse(this.companyService.editCompany(id,
                companyMapper.toEntity(companyRequest)));
    }
}
