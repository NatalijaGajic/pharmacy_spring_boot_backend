package com.example.reservation_app.dto;

import java.util.List;

public class ReservationMedicineCreationDto {
	Integer reservationId;
	List<MedicineWithAmountDto> medicinesWithAmount;
	
	public ReservationMedicineCreationDto(Integer reservationId, List<MedicineWithAmountDto> medicinesWithAmount) {
		super();
		this.reservationId = reservationId;
		this.medicinesWithAmount = medicinesWithAmount;
	}
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