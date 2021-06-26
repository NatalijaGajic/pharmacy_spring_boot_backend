package com.example.user_alergies_app.util;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.example.user_alergies_app.dto.MedicineDto;
import com.example.user_alergies_app.dto.MedicineIdsDto;

public interface MedicineService {
	public Collection<MedicineDto> getMedicinesByIds(MedicineIdsDto medicineIdsDto);
}