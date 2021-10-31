package com.afs.restfulapi.service;

import com.afs.restfulapi.entity.Company;
import com.afs.restfulapi.entity.Employee;
import com.afs.restfulapi.exception.CompanyNotFoundException;
import com.afs.restfulapi.repository.CompanyRepository;
import com.afs.restfulapi.repository.EmployeeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CompanyService {
    
    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;
    
    public CompanyService(CompanyRepository companyRepository, EmployeeRepository employeeRepository) {
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
    }
    
    public List<Company> findAll() {
        return this.companyRepository.findAll();
    }
    
    public Company findById(Integer id){
        return this.companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);
    }

    public List<Company> findPagingCompanies(int page, int pageSize) {
        return this.companyRepository.findAll(PageRequest.of(page, pageSize)).getContent();
    }

    public Company editCompany(Integer id, Company updatedCompany) {
        Company originCompany =
                companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);
        if (updatedCompany.getName() != null) {
            originCompany.setName(updatedCompany.getName());
        }
        if (updatedCompany.getEmployees() != null) {
            originCompany.setEmployees(updatedCompany.getEmployees());
        }
        return this.companyRepository.save(originCompany);
    }

    public Company createCompany(Company company) {
        return this.companyRepository.save(company);
    }

    public void deleteCompany(Integer id){
        Company company = this.findById(id);
        this.companyRepository.delete(company);
    }

    public void deleteAllCompany() {
        this.companyRepository.deleteAll();
    }

    public List<Employee> getEmployeeListInCompanyById(Integer id) {
        return this.employeeRepository.findAllByCompanyId(id);
    }

    //todo: add search employee by company id
}
