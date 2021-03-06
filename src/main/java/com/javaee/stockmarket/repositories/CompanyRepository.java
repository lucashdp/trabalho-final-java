package com.javaee.stockmarket.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.javaee.stockmarket.domain.*;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long>{

}