package com.example.user_alergies_app.dto;

import com.example.pharmacy.dto.MedicineDto;

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


