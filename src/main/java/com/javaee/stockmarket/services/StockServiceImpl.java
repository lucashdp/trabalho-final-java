package com.javaee.stockmarket.services;

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
    public List<StockDTO> getAllStock() {
        return StreamSupport.stream(this.stockRepository.findAll().spliterator(), false).map(stockMapper::stockToStockDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StockDTO getById(Long id) {
        Stock stock = getStockById(id);
        return stockMapper.stockToStockDTO(stock);
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
    public StockDTO createNew(StockDTO stockDTO) {
        Stock detachedStock = stockMapper.stockDTOToStock(stockDTO);
        Stock stockSaved = stockRepository.save(detachedStock);
        return stockMapper.stockToStockDTO(stockSaved);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public StockDTO save(Long id, StockDTO stockDTO) {
        Stock detachedStock = stockMapper.stockDTOToStock(stockDTO);
        detachedStock.setId(id);
        Stock stockSaved = stockRepository.save(detachedStock);
        return stockMapper.stockToStockDTO(stockSaved);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(Long id) {
        stockRepository.deleteById(id);
    }
}