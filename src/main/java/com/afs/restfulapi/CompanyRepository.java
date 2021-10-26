package com.afs.restfulapi;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository

public class CompanyRepository {

    private final List<Company> companies = new ArrayList<>();

    List<String> employees = Arrays.asList("John", "Peter", "Mary");

    public List<Company> findAll(){
        return companies;
    }

    public CompanyRepository() {
        companies.add(new Company(1, "Agile", "StartUp", employees));
    }

    public Company findById(Integer id){
        return this.companies.stream().filter(item->id.equals(item.getId())).findFirst().orElseThrow(EmployeeNotFoundException::new);
    }


}
