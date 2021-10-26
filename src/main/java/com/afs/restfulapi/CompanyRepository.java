package com.afs.restfulapi;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository

public class CompanyRepository {

    private final List<Company> companies = new ArrayList<>();

    List<String> employees;

    public CompanyRepository() {
        companies.add(new Company(1, "Agile", "StartUp", new ArrayList<>()));
    }

}
