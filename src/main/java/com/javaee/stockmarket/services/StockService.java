package com.javaee.stockmarket.services;

import java.util.List;

import com.javaee.stockmarket.api.v1.model.StockDTO;

public interface StockService {

	StockDTO getById(Long id);

    List<StockDTO> getAllStock();

    StockDTO createNew(StockDTO stock);

    StockDTO save(Long id, StockDTO stock);

    void deleteById(Long id);
}