package com.javaee.stockmarket.api.v1.mapper;

import org.springframework.stereotype.Component;

import java.util.Optional;

import com.javaee.stockmarket.api.v1.model.StockDTO;
import com.javaee.stockmarket.api.v1.model.StockViewDTO;
import com.javaee.stockmarket.api.v1.model.UserDTO;
import com.javaee.stockmarket.domain.Company;
import com.javaee.stockmarket.domain.Stock;
import com.javaee.stockmarket.domain.User;
import com.javaee.stockmarket.repositories.*;

@Component
public class StockMapper {

    private CompanyMapper companyMapper;
    private UserMapper userMapper;

    private CompanyRepository companyRepository;
    private UserRepository userRepository;

    public StockMapper(CompanyMapper companyMapper, UserMapper userMapper, CompanyRepository companyRepository,
            UserRepository userRepository) {
        this.companyMapper = companyMapper;
        this.userMapper = userMapper;

        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
    }

    public StockViewDTO stockToStockViewDTO(Stock stock) {
        final StockViewDTO stockViewDTO = new StockViewDTO();
        stockViewDTO.setId(stock.getId());
        stockViewDTO.setPrice(stock.getPrice());
        stockViewDTO.setInitialPrice(stock.getInitialPrice());
        stockViewDTO.setPurchaseDate(stock.getPurchaseDate());
        stockViewDTO.setCompany(companyMapper.companyToCompanyDTO(stock.getCompany()));
        stockViewDTO.setOwner(getOwnerDto(stock));
        return stockViewDTO;
    }

    private UserDTO getOwnerDto(Stock stock) {
        if (stock.getOwner() != null)
            return userMapper.userToUserDTO(stock.getOwner());
        else
            return null;
    }

    private void fillCompany(Long company_id, final Stock stock) {
        if (company_id != null) {
            Optional<Company> companyOptional = companyRepository.findById(company_id);
            if (!companyOptional.isPresent()) {
                throw new IllegalArgumentException("Company Not Found For ID value: " + company_id);
            }
            stock.setCompany(companyOptional.get());
        } else {
            throw new IllegalArgumentException("Company Not Found For ID value: " + company_id);
        }
    }

    private void fillOwner(Long owner_id, final Stock stock) {
        if (owner_id != null && owner_id != 0) {
            Optional<User> userOptional = userRepository.findById(owner_id);
            if (!userOptional.isPresent()) {
                throw new IllegalArgumentException("User Not Found For ID value: " + owner_id);
            }
            stock.setOwner(userOptional.get());
        }
    }

    public StockDTO stockToStockDTO(Stock stock) {
        final StockDTO stockDTO = new StockDTO();
        stockDTO.setId(stock.getId());
        stockDTO.setPrice(stock.getPrice());
        stockDTO.setInitialPrice(stock.getInitialPrice());
        stockDTO.setCompany_id(stock.getCompany().getId());
        stockDTO.setOwner_id(stock.getOwner().getId());
        return stockDTO;
    }

    public Stock stockDTOToStock(StockDTO stockDTO) {
        final Stock stock = new Stock();
        stock.setId(stockDTO.getId());
        stock.setPrice(stockDTO.getPrice());
        stock.setInitialPrice(stockDTO.getInitialPrice());
        fillCompany(stockDTO.getCompany_id(), stock);
        fillOwner(stockDTO.getOwner_id(), stock);
        return stock;
    }
}