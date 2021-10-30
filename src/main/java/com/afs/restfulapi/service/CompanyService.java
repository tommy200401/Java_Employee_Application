package com.afs.restfulapi.service;

import com.afs.restfulapi.entity.Company;
import com.afs.restfulapi.exception.CompanyNotFoundException;
import com.afs.restfulapi.repository.CompanyRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    
    private final CompanyRepository companyRepository;
    
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
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

    //todo: add search employee by company id
}
