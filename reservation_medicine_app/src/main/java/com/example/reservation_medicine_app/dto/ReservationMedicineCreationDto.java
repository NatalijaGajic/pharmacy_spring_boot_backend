package com.example.reservation_medicine_app.dto;

import java.util.List;

public class ReservationMedicineCreationDto {
	Integer reservationId;
	List<MedicineWithAmountDto> medicinesWithAmount;
	
	public ReservationMedicineCreationDto() {
		super();
	}
	public Integer getReservationId() {
		return reservationId;
	}
	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}
	public List<MedicineWithAmountDto> getMedicinesWithAmount() {
		return medicinesWithAmount;
	}
	public void setMedicinesWithAmount(List<MedicineWithAmountDto> medicinesWithAmount) {
		this.medicinesWithAmount = medicinesWithAmount;
	}
}
