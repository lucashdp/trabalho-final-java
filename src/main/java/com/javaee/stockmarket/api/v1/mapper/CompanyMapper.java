package com.javaee.stockmarket.api.v1.mapper;

import org.springframework.stereotype.Component;

import com.javaee.stockmarket.api.v1.model.CompanyDTO;
import com.javaee.stockmarket.domain.Company;;

@Component
public class CompanyMapper {
    public CompanyDTO companyToCompanyDTO(Company company) {
        final CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(company.getId());
        companyDTO.setName(company.getName());
        return companyDTO;
    }

    public Company companyDTOToCompany(CompanyDTO companyDTO) {
        final Company company = new Company();
        company.setId(companyDTO.getId());
        company.setName(companyDTO.getName());
        return company;
    }
}