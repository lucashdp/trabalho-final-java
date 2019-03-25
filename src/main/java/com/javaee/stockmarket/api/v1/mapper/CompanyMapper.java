package com.javaee.stockmarket.api.v1.mapper;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import com.javaee.stockmarket.api.v1.model.CompanyDTO;
import com.javaee.stockmarket.domain.Company;
import com.javaee.stockmarket.domain.Stock;
import com.javaee.stockmarket.repositories.StockRepository;;

@Component
public class CompanyMapper {
    private StockRepository stockRepository;

    public CompanyMapper(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public CompanyDTO companyToCompanyDTO(Company company) {
        final CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(company.getId());
        companyDTO.setName(company.getName());
        companyDTO.setInitial_stock_price(company.getInitial_stock_price());
        companyDTO.setStock_number(getStock_number(company));
        return companyDTO;
    }

    private Long getStock_number(Company company) {
        if (company != null) {
            List<Stock> stockList = stockRepository.findByCompany(company);
            return new Long(stockList.size());
        } else {
            return new Long(0);
        }
    }

    public Company companyDTOToCompany(CompanyDTO companyDTO) {
        final Company company = new Company();
        company.setId(companyDTO.getId());
        company.setName(companyDTO.getName());
        company.setInitial_stock_price(companyDTO.getInitial_stock_price());
        return company;
    }
}