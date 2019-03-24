package com.javaee.stockmarket.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Actions")
@Data
@Getter
@Setter
public class Action {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JsonIgnoreProperties("actions")
	private Company company;

	@ManyToOne
	@JsonIgnoreProperties("actions")
	private User user;
}