package com.example.reservation_medicine_app.model;

import java.util.ArrayList;

public class ReservationMedicine {
	private Integer id;
	
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

	public Integer getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(Integer medicineId) {
		this.medicineId = medicineId;
	}

	public Integer getReservationId() {
		return reservationId;
	}

	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}

	private Integer amount;
	
	private Integer medicineId;
	
	private Integer reservationId;
	
	public ReservationMedicine(Integer id, Integer amount, Integer medicineId, Integer reservationId) {
		super();
		this.id = id;
		this.amount = amount;
		this.medicineId = medicineId;
		this.reservationId = reservationId;
	}
}

//