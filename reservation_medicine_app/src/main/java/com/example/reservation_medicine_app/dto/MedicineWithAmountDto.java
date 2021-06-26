package com.example.reservation_medicine_app.dto;

public class MedicineWithAmountDto {
	Integer amount;
	Integer medicineId;
	public Integer getMedicineId() {
		return medicineId;
	}
	public void setMedicineId(Integer medicineId) {
		this.medicineId = medicineId;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public MedicineWithAmountDto(Integer medicineId, Integer amount) {
		super();
		this.medicineId = medicineId;
		this.amount = amount;
	}
}
