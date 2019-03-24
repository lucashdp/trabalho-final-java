package com.javaee.stockmarket.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "stock")
@Data
@Getter
@Setter
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Float price;
	
	private Float initialPrice;
	
	private LocalDateTime purchaseDate;

	@ManyToOne
	private Company company;

	@ManyToOne
	private User owner;
}