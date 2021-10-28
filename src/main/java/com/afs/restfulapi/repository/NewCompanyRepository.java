package com.afs.restfulapi.repository;

import com.afs.restfulapi.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface NewCompanyRepository extends JpaRepository<Company, Integer> {

}
