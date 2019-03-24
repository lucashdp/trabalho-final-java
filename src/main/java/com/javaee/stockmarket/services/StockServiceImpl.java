package com.javaee.stockmarket.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

import com.javaee.stockmarket.domain.Stock;

import com.javaee.stockmarket.api.v1.model.*;
import com.javaee.stockmarket.repositories.*;
import com.javaee.stockmarket.api.v1.mapper.*;

@Service
public class StockServiceImpl implements StockService {

    private StockRepository stockRepository;

    private final StockMapper stockMapper;

    public StockServiceImpl(StockRepository stockRepository, StockMapper stockMapper) {
        this.stockRepository = stockRepository;
        this.stockMapper = stockMapper;
    }

    @Override
    public List<StockViewDTO> getAllStock() {
        return StreamSupport.stream(this.stockRepository.findAll().spliterator(), false)
                .map(stockMapper::stockToStockViewDTO).collect(Collectors.toList());
    }

    @Override
    public StockViewDTO getById(Long id) {
        Stock stock = getStockById(id);
        return stockMapper.stockToStockViewDTO(stock);
    }

    private Stock getStockById(Long id) {
        Optional<Stock> stockOptional = stockRepository.findById(id);

        if (!stockOptional.isPresent()) {
            throw new IllegalArgumentException("Stock Not Found For ID value: " + id.toString());
        }
        return stockOptional.get();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public StockViewDTO createNew(StockDTO stockDTO) {
        Stock detachedStock = stockMapper.stockDTOToStock(stockDTO);
        detachedStock.setPurchaseDate(LocalDateTime.now());
        Stock stockSaved = stockRepository.save(detachedStock);

        return stockMapper.stockToStockViewDTO(stockSaved);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public StockViewDTO save(Long id, StockDTO stockDTO) {
        Stock detachedStock = stockMapper.stockDTOToStock(stockDTO);
        detachedStock.setPurchaseDate(LocalDateTime.now());
        detachedStock.setId(id);
        Stock stockSaved = stockRepository.save(detachedStock);
        return stockMapper.stockToStockViewDTO(stockSaved);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(Long id) {
        stockRepository.deleteById(id);
    }
}