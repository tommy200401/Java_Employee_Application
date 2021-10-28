package com.afs.restfulapi.repository;

import com.afs.restfulapi.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewEmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findAllByGender(String gender);

    List<Employee> findAllByCompanyId(Integer id);


}
