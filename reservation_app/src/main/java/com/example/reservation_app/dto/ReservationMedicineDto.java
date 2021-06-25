package com.example.reservation_app.dto;

public class ReservationMedicineDto {

	private Integer id;

	private Integer amount;
	
	public ReservationMedicineDto() {
		super();
	}

	public ReservationMedicineDto(Integer id, Integer amount) {
		super();
		this.id = id;
		this.amount = amount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
}
