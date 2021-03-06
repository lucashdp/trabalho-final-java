package com.javaee.stockmarket.api.v1.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockDTO {

	private Long id;
	
	private Float price;
	
	private Float initialPrice;

	private Long company_id;

	private Long owner_id;
}