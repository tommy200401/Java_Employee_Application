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

    // /companies/{id}
    @GetMapping("/{id}")
    public Company findById(@PathVariable Integer id) {
        return this.companyRepository.findById(id);
    }

    // /companies/{id}/employees
    @GetMapping("/{id}/employees")
    public List<String> findEmployeesById(@PathVariable Integer id) {
        return this.companyRepository.findEmployeesById(id);
    }

    // /companies?page=1&pageSize=5
    @GetMapping(params = {"page", "size"})
    public PageImpl<Company> findByPageAndPageSize(@PageableDefault Pageable pageable) {
        return this.companyRepository.findPagingCompanies(pageable);
    }

    // post
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)  // Code=201
    public Company createCompany(@RequestBody Company company) {
        return this.companyRepository.createCompany(company);
    }

    // delete
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)  // Code=204
    public void deleteById(@PathVariable Integer id) {
        this.companyRepository.deleteById(id);
    }

    // Put
    @PutMapping("/{id}")
    public Company editCompany(@PathVariable Integer id, @RequestBody Company updatedCompany) {
        Company originCompany = this.companyRepository.findById(id);
        if (updatedCompany.getBasicInfo() != null) {
            originCompany.setBasicInfo(updatedCompany.getBasicInfo());
        }
        if (updatedCompany.getEmployees() != null) {
            originCompany.setEmployees(updatedCompany.getEmployees());
        }
        return this.companyRepository.save(id, originCompany);
    }
}
