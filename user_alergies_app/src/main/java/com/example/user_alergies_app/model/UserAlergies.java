package com.example.user_alergies_app.model;

public class UserAlergies {

	private Integer userId;

	private Integer medicineId;
	
	public UserAlergies(Integer userId, Integer medicineId) {
		super();
		this.userId = userId;
		this.medicineId = medicineId;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(Integer medicineId) {
		this.medicineId = medicineId;
	}
}
