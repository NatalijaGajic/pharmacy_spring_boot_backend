package com.example.reservation_medicine_app.dto;

import com.example.pharmacy.dto.MedicineDto;

public class ReservationMedicineDto {
	MedicineDto medicine;
	Integer amount;

	public ReservationMedicineDto() {
		super();
	}

	public ReservationMedicineDto(MedicineDto medicine, Integer amount) {
		super();
		this.medicine = medicine;
		this.amount = amount;
	}
	
	public MedicineDto getMedicine() {
		return medicine;
	}
	public void setMedicine(MedicineDto medicine) {
		this.medicine = medicine;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	
}
