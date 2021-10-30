package com.afs.restfulapi.controller;

import com.afs.restfulapi.entity.Company;
import com.afs.restfulapi.entity.Employee;
import com.afs.restfulapi.repository.CompanyRepository;
import com.afs.restfulapi.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Sql(statements = "alter table company alter column id restart with 1")
@SpringBootTest
@AutoConfigureMockMvc
class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    ObjectMapper jsonMapper;

    @BeforeEach
    void setUp(){

        companyRepository.deleteAll();
        employeeRepository.deleteAll();
    }

    @Test
    void should_return_all_companies_when_get_given_two_companies() throws Exception {
        //given
        Company company1 = new Company("AAPL");
        Company company2 = new Company("TSLA");
        companyRepository.save(company1);
        companyRepository.save(company2);

        //when
        ResultActions resultActions = mockMvc.perform(get("/companies"));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("AAPL"))

                .andExpect(jsonPath("$[1].id").value("2"))
                .andExpect(jsonPath("$[1].name").value("TSLA"));
    }

    @Test
    void should_return_correct_company_when_get_given_company_id() throws Exception{
        //given
        Company company1 = new Company("AAPL");
        Company company2 = new Company("TSLA");
        companyRepository.save(company1);
        companyRepository.save(company2);

        //when
        ResultActions resultActions = mockMvc.perform(get("/companies/2"));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("2"))
                .andExpect(jsonPath("$.name").value("TSLA"));
    }

    @Test
    void should_return_paged_companies_when_get_companies_when_get_page_given_page_and_page_size() throws Exception {
        //given
        Company company1 = new Company("AAPL");
        Company company2 = new Company("TSLA");
        Company company3 = new Company("FORD");
        companyRepository.save(company1);
        companyRepository.save(company2);
        companyRepository.save(company3);

        //when
        ResultActions resultActions = mockMvc.perform(get("/employees?page=1&pageSize=2"));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(3))
                .andExpect(jsonPath("$[0].name").value("FORD"));
    }

    @Test
    void should_create_employee_when_post_given_an_employee() throws Exception {
        //given
        Company company = new Company("AAPL");
        String url = "/companies";
        //when
        ResultActions result = mockMvc.perform(
                post(url)
                        .contentType(APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(company))
        );
        //then
        result
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("AAPL"));
    }

    @Test
    void should_update_company_when_put_given_an_updated_company() throws Exception {
        //given
        Company company = new Company("AAPL");
        Company updated = companyRepository.save(company);
        updated.setName("Apple");
        String url = String.format("/companies/%d", updated.getId());
        //when
        ResultActions result = mockMvc.perform(
                put(url)
                        .contentType(APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(updated))
        );
        //then
        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(updated.getId()))
                .andExpect(jsonPath("$.name").value(updated.getName()))
        ;
    }

    @Test
    void should_delete_company_when_given_an_company_id() throws Exception {
        //given
        Company company = new Company("AAPL");
        Company saved = companyRepository.save(company);
        String url = String.format("/company/%d", saved.getId());
        //then
        assertEquals(1, companyRepository.findAll().size());
        ResultActions result = mockMvc.perform(delete(url));
        assertEquals(0, companyRepository.findAll().size());
        result
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$").doesNotExist())
        ;
    }
}