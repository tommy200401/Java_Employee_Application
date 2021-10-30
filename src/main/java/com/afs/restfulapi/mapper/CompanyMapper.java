package com.afs.restfulapi.mapper;

import com.afs.restfulapi.dto.CompanyRequest;
import com.afs.restfulapi.dto.CompanyResponse;
import com.afs.restfulapi.entity.Company;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CompanyMapper {
    public EmployeeMapper employeeMapper;

    public Company toEntity(CompanyRequest companyRequest) {
        Company company = new Company();
        BeanUtils.copyProperties(companyRequest, company);
        return company;
    }

    public CompanyResponse toResponse(Company company){
        CompanyResponse companyResponse = new CompanyResponse();
        companyResponse.setId(company.getCompanyId());
        companyResponse.setName(company.getCompanyName());
        companyResponse.setEmployees(company.getEmployees().stream()
                .map(employee -> employeeMapper.toResponse(employee)).collect(Collectors.toList()));
        return companyResponse;
    }
}

