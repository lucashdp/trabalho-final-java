package com.javaee.stockmarket.api.v1.mapper;

import org.springframework.stereotype.Component;

import com.javaee.stockmarket.api.v1.model.StockDTO;
import com.javaee.stockmarket.api.v1.model.StockViewDTO;
import com.javaee.stockmarket.domain.Stock;

@Component
public class StockMapper {

    private CompanyMapper companyMapper;
    private UserMapper userMapper;

    public StockViewDTO stockToStockViewDTO(Stock stock) {
        final StockViewDTO stockViewDTO = new StockViewDTO();
        stockViewDTO.setId(stock.getId());
        stockViewDTO.setPrice(stock.getPrice());
        stockViewDTO.setInitialPrice(stock.getInitialPrice());
        stockViewDTO.setPurchaseDate(stock.getPurchaseDate());
        stockViewDTO.setCompany(companyMapper.companyToCompanyDTO(stock.getCompany()));
        stockViewDTO.setOwner(userMapper.userToUserDTO(stock.getOwner()));
        return stockViewDTO;
    }

    public Stock stockViewDTOToStock(StockViewDTO stockViewDTO) {
        final Stock stock = new Stock();
        stock.setId(stockViewDTO.getId());
        stock.setPrice(stockViewDTO.getPrice());
        stock.setInitialPrice(stockViewDTO.getInitialPrice());
        stock.setPurchaseDate(stockViewDTO.getPurchaseDate());
        stock.setCompany(companyMapper.companyDTOToCompany(stockViewDTO.getCompany()));
        stock.setOwner(userMapper.userDTOToUser(stockViewDTO.getOwner()));
        return stock;
    }

    public StockDTO stockToStockDTO(Stock stock) {
        final StockDTO stockDTO = new StockDTO();
        stockDTO.setId(stock.getId());
        stockDTO.setPrice(stock.getPrice());
        stockDTO.setInitialPrice(stock.getInitialPrice());
        return stockDTO;
    }

    public Stock stockDTOToStock(StockDTO stockDTO) {
        final Stock stock = new Stock();
        stock.setId(stockDTO.getId());
        stock.setPrice(stockDTO.getPrice());
        stock.setInitialPrice(stockDTO.getInitialPrice());
        return stock;
    }
}