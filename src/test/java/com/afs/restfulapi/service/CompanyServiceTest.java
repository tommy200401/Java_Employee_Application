package com.afs.restfulapi.service;

import com.afs.restfulapi.entity.Company;
import com.afs.restfulapi.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

class CompanyServiceTest {

    @Mock
    CompanyRepository companyRepository;

    @InjectMocks
    CompanyService companyService;

    @Test
    void should_return_all_companies_when_find_all_given_2_companies(){
        //given
        List<Company> companies = Arrays.asList(
                new Company("AAPL"),
                new Company("TSLA"));
        when(companyRepository.findAll()).thenReturn(companies);

        //when
        List<Company> actual = companyService.findAll();

        //then
        assertEquals(companies, actual);
    }

    @Test
    void should_return_a_company_when_find_by_id_given_id() {
        //given
        Company company = new Company("AAPL");
        when(companyRepository.findById(1)).thenReturn(java.util.Optional.of(company));

        //when
        Company actual = companyService.findById(1);

        //then
        assertSame(company, actual);
    }

    @Test
    void should_return_pages_of_companies_when_find_by_page_and_pageSize_given_page(){
        //given
        List<Company> companies = Arrays.asList(
                new Company("AAPL"),
                new Company("TSLA"),
                new Company("FORD"));
        Pageable pageable = PageRequest.of(0, 2);
        when(companyRepository.findAll(pageable)).thenReturn(new PageImpl<>(companies));
        //when
        List<Company> actual = companyService.findPagingCompanies(0,2);
        //then
        assertEquals(companies, actual);
    }

    @Test
    void should_return_new_company_when_add_company_given_company(){
        //given
        Company company1 = new Company("AAPL");
        Company company2 = new Company(2, "AAPL");
        when(companyRepository.save(company1)).thenReturn(company2);

        //when
        Company actual = companyService.createCompany(company1);

        //then
        assertNotNull(actual.getId());
        assertEquals(company1.getName(), actual.getName());
    }

    @Test
    void should_return_updated_company_when_edit_company_given_company_update_info(){
        //given
        Company company = new Company("AAPL");
        companyRepository.save(company);
        Company companyUpdated = new Company("Apple");
        when(companyRepository.save(companyUpdated)).thenReturn(companyUpdated);

        //when
        Company actual = companyRepository.save(companyUpdated);

        //then
        assertEquals(companyUpdated, actual);
    }


    @Test
    void should_delete_company_when_delete_company_given_company_id(){
        //given
        willDoNothing().given(companyRepository).deleteById(anyInt());
        companyService.deleteCompany(1);
        //then
        verify(companyRepository).deleteById(anyInt());
        verifyNoMoreInteractions(companyRepository);
    }

}