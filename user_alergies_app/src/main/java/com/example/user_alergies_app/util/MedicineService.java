package com.example.user_alergies_app.util;

import java.util.Collection;

import com.example.pharmacy.dto.MedicineDto;
import com.example.pharmacy.dto.MedicineIdsDto;

public interface MedicineService {
	public Collection<MedicineDto> getMedicinesByIds(MedicineIdsDto medicineIdsDto);
}
