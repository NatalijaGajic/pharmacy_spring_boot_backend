package com.example.user_alergies_app.dto;

import java.util.Collection;
import java.util.List;

public class UserAlergiesCreationDTO {
	
	private Integer userId;

	private List<Integer> medicineIds;
	
	public UserAlergiesCreationDTO() {
		super();
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<Integer> getMedicineIds() {
		return medicineIds;
	}

	public void setMedicineIds(List<Integer> medicineIds) {
		this.medicineIds = medicineIds;
	}

}
