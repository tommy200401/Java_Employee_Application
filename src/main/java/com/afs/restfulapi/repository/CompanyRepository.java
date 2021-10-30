package com.afs.restfulapi.repository;

import com.afs.restfulapi.entity.Company;
import com.afs.restfulapi.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {


}
