package com.javaee.stockmarket.services;

import java.util.List;

import com.javaee.stockmarket.api.v1.model.CompanyDTO;

public interface CompanyService {

	CompanyDTO getById(Long id);

    List<CompanyDTO> getAllCompanies();

    CompanyDTO createNew(CompanyDTO company);

    CompanyDTO save(Long id, CompanyDTO company);

    void deleteById(Long id);

    void deleteStockByCompany(Long id);
}