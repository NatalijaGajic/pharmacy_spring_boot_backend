package com.example.user_alergies_app.dto;


public class UserAlergiesDto {
	
	MedicineDto medicine;

	public UserAlergiesDto(MedicineDto medicine) {
		super();
		this.medicine = medicine;
	}

	public UserAlergiesDto() {
		super();
	}
	
	public MedicineDto getMedicine() {
		return medicine;
	}
	public void setMedicine(MedicineDto medicine) {
		this.medicine = medicine;
	}
		
}


