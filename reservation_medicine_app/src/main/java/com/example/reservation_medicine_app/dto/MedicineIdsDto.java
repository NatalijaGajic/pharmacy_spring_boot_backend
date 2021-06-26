package com.example.reservation_medicine_app.dto;

import java.util.Collection;

public class MedicineIdsDto {
	private Collection<Integer> medicineIds;
	
	public void setMedicineIds(Collection<Integer> medicineIds) {
		this.medicineIds = medicineIds;
	}

	public Collection<Integer> getMedicineIds() {
		return this.medicineIds;
	}
}