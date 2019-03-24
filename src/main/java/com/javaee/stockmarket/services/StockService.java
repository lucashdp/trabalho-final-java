package com.javaee.stockmarket.services;

import java.util.List;

import com.javaee.stockmarket.api.v1.model.StockDTO;
import com.javaee.stockmarket.api.v1.model.StockViewDTO;

public interface StockService {

	StockViewDTO getById(Long id);

    List<StockViewDTO> getAllStock();

    StockViewDTO createNew(StockDTO stock);

    StockViewDTO save(Long id, StockDTO stock);
}