package com.javaee.stockmarket.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

import com.javaee.stockmarket.domain.Company;
import com.javaee.stockmarket.domain.Stock;
import com.javaee.stockmarket.api.v1.model.*;
import com.javaee.stockmarket.repositories.*;
import com.javaee.stockmarket.api.v1.mapper.*;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;
    private StockRepository stockRepository;

    private final CompanyMapper companyMapper;
    private final StockMapper stockMapper;

    public CompanyServiceImpl(CompanyRepository companyRepository, CompanyMapper companyMapper,
            StockRepository stockRepository, StockMapper stockMapper) {
        this.companyRepository = companyRepository;
        this.stockRepository = stockRepository;

        this.companyMapper = companyMapper;
        this.stockMapper = stockMapper;
    }

    @Override
    public List<CompanyDTO> getAllCompanies() {
        return StreamSupport.stream(this.companyRepository.findAll().spliterator(), false)
                .map(companyMapper::companyToCompanyDTO).collect(Collectors.toList());
    }

    @Override
    public CompanyDTO getById(Long id) {
        Company company = getCompanyById(id);
        return companyMapper.companyToCompanyDTO(company);
    }

    private Company getCompanyById(Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);

        if (!companyOptional.isPresent()) {
            throw new IllegalArgumentException("Company Not Found For ID value: " + id.toString());
        }
        return companyOptional.get();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CompanyDTO createNew(CompanyDTO companyDTO) {
        Company detachedCompany = companyMapper.companyDTOToCompany(companyDTO);

        Company companySaved = companyRepository.save(detachedCompany);

        Long stock_number = companyDTO.getStock_number();
        if (stock_number > 0)
            createStock(companySaved.getId(), stock_number, companySaved.getInitial_stock_price());

        return companyMapper.companyToCompanyDTO(companySaved);
    }

    private void createStock(Long company_id, Long stock_number, Float initial_stock_price) {
        for (int i = 0; i < stock_number; i++) {
            StockDTO stockDTO = new StockDTO();
            stockDTO.setCompany_id(company_id);
            stockDTO.setInitialPrice(initial_stock_price);
            stockDTO.setPrice(new Float(0));

            Stock stock = stockMapper.stockDTOToStock(stockDTO);

            stockRepository.save(stock);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CompanyDTO save(Long id, CompanyDTO companyDTO) {
        Company detachedCompany = companyMapper.companyDTOToCompany(companyDTO);
        detachedCompany.setId(id);

        List<Stock> stockList = stockRepository.findByCompany(detachedCompany);
        int previous_stock_number = stockList.size();

        Company companySaved = companyRepository.save(detachedCompany);

        Long requested_stock_number = companyDTO.getStock_number();

        Long difference_stock_number = requested_stock_number - previous_stock_number;

        if (difference_stock_number > 0)
            createStock(companySaved.getId(), difference_stock_number, companySaved.getInitial_stock_price());

        return companyMapper.companyToCompanyDTO(companySaved);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }
}