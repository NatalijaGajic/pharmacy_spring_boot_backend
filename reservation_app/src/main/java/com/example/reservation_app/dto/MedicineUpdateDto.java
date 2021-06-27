package com.example.reservation_app.dto;

public class MedicineUpdateDto {

	private String name;
	
	private double price;
	
	private String manufacturer;
	
	private String description;
	
	private Integer quantity;
	
	public MedicineUpdateDto() {
		
	}
	
	public MedicineUpdateDto(String name, double price, String manufacturer, String description, Integer quantity) {
		super();
		this.name = name;
		this.price = price;
		this.manufacturer = manufacturer;
		this.description = description;
		this.quantity = quantity;
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
