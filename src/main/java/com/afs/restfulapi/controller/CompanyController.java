package com.afs.restfulapi.controller;

import com.afs.restfulapi.dto.CompanyRequest;
import com.afs.restfulapi.dto.CompanyResponse;
import com.afs.restfulapi.dto.EmployeeResponse;
import com.afs.restfulapi.entity.Company;
import com.afs.restfulapi.entity.Employee;
import com.afs.restfulapi.mapper.CompanyMapper;
import com.afs.restfulapi.repository.CompanyRepository;
import com.afs.restfulapi.service.CompanyService;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private CompanyService companyService;
    private CompanyMapper companyMapper;

    public CompanyController(CompanyService companyService, CompanyMapper companyMapper) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
    }

    @GetMapping
    public List<CompanyResponse> findAllCompanies() {
        return this.companyService.findAll().stream().map(company->companyMapper.toResponse(company)).collect(Collectors.toList());;
    }

    // /companies/{id}
    @GetMapping("/{id}")
    public CompanyResponse findById(@PathVariable Integer id) {
        return this.companyMapper.toResponse(companyService.findById(id));
    }

    // Todo: add method
//     // /companies/{id}/employees
//    @GetMapping("/{id}/employees")
//    public List<CompanyResponse> getEmployeeListInCompanyById(@PathVariable Integer id) {
//        return this.companyService.getEmployeeListInCompanyById(id)
//                .stream()
//                .map(companyMapper::toResponse)
//                .collect(Collectors.toList());
//    }

    // /companies?page=1&pageSize=5
    @GetMapping(params = {"page", "pageSize"})
    public List<CompanyResponse> findByPageAndPageSize(@RequestParam Integer page,
                                                        @RequestParam Integer pageSize) {
        return companyService.findPagingCompanies(page, pageSize)
                .stream()
                .map(companyMapper::toResponse)
                .collect(Collectors.toList());
    }

    // post
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)  // Code=201
    public Company createCompany(@RequestBody CompanyRequest companyRequest) {
        return this.companyService.createCompany(companyMapper.toEntity(companyRequest));
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
    public Company editCompany(@PathVariable Integer id,
                          @RequestBody Company updatedCompany) {
        return this.companyService.editCompany(id, updatedCompany);
    }
}
