package com.afs.restfulapi.repository;

import com.afs.restfulapi.entity.Company;
import com.afs.restfulapi.entity.Employee;
import com.afs.restfulapi.exception.CompanyNotFoundException;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository

public class CompanyRepository {

    private final List<Company> companies = new ArrayList<>();

    public List<Company> findAll() {
        return companies;
    }

    public Company findById(Integer id) {
        return this.companies.stream().filter(item -> id.equals(item.getCompanyId())).findFirst().orElseThrow(CompanyNotFoundException::new);
    }

    public PageImpl<Company> findPagingCompanies(Pageable pageable) {
        List<Company> page = this.companies.stream().skip((long) pageable.getPageNumber() * pageable.getPageSize()).limit(pageable.getPageSize()).collect(Collectors.toList());
        return new PageImpl<>(page, pageable, this.companies.size());
    }

    public Company createCompany(Company company) {
        int id = this.companies.stream().mapToInt(Company::getCompanyId).max().orElse(0) + 1;
        company.setCompanyId(id);
        this.companies.add(company);
        return company;
    }

    public void deleteById(Integer id) {
        Company company = this.findById(id);
    }

    public Company save(Integer id, Company updatedCompany) {
        this.deleteById(id);
        this.companies.add(updatedCompany);
        return updatedCompany;
    }

    public List<Employee> findEmployeesById(Integer id) { return null;
    }
}
