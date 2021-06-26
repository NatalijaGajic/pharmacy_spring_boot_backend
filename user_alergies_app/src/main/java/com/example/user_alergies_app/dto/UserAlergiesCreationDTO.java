package com.example.user_alergies_app.dto;

import java.util.Collection;

public class UserAlergiesCreationDTO {
	
	private Integer userId;

	private Collection<Integer> medicineIds;
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Collection<Integer> getMedicineIds() {
		return medicineIds;
	}

	public void setMedicineIds(Collection<Integer> medicineIds) {
		this.medicineIds = medicineIds;
	}

}
