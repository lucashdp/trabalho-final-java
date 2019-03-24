package com.javaee.stockmarket.api.v1.mapper;

import org.springframework.stereotype.Component;

import com.javaee.stockmarket.api.v1.model.StockDTO;
import com.javaee.stockmarket.domain.Stock;

@Component
public class StockMapper {

    private CompanyMapper companyMapper;
    private UserMapper userMapper;

    public StockDTO stockToStockDTO(Stock stock) {
        final StockDTO stockDTO = new StockDTO();
        stockDTO.setId(stock.getId());
        stockDTO.setPrice(stock.getPrice());
        stockDTO.setInitialPrice(stock.getInitialPrice());
        stockDTO.setPurchaseDate(stock.getPurchaseDate());
        stockDTO.setCompany(companyMapper.companyToCompanyDTO(stock.getCompany()));
        stockDTO.setOwner(userMapper.userToUserDTO(stock.getOwner()));
        return stockDTO;
    }

    public Stock stockDTOToStock(StockDTO stockDTO) {
        final Stock stock = new Stock();
        stock.setId(stockDTO.getId());
        stock.setPrice(stockDTO.getPrice());
        stock.setInitialPrice(stockDTO.getInitialPrice());
        stock.setPurchaseDate(stockDTO.getPurchaseDate());
        stock.setCompany(companyMapper.companyDTOToCompany(stockDTO.getCompany()));
        stock.setOwner(userMapper.userDTOToUser(stockDTO.getOwner()));
        return stock;
    }
}