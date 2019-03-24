package com.javaee.stockmarket.api.v1.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyDTO {
	private Long id;
	
	@NotBlank
	@Size(min = 2, max = 255)
	private String name;

	private Long stock_number;
}