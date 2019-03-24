package com.javaee.stockmarket.api.v1.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockDTO {

	private Long id;
	
	private Float price;
	
	private Float initialPrice;
	
	private LocalDateTime purchaseDate;

	private CompanyDTO company;

	private UserDTO owner;
}