package com.javaee.stockmarket.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.javaee.stockmarket.domain.*;

@Repository
public interface StockRepository extends CrudRepository<Stock, Long>{

	List<Stock> findByCompany(Company company);
}