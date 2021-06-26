package com.example.medicine_app.jpa;

import java.sql.Date;
import java.util.List;


import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Medicine {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private double price;
	
	private String manufacturer;
	
	private String description;
	
	@Column(nullable = false)
	private Integer quantity;
	
	public Medicine(String name, double price, String manufacturer, String description, Integer quantity) {
		super();
		this.name = name;
		this.price = price;
		this.manufacturer = manufacturer;
		this.description = description;
		this.quantity = quantity;
	}
	
	public Medicine() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
}
