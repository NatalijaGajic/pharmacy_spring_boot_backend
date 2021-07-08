package com.example.reservation_app.utils;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.example.pharmacy.dto.MedicineIdsDto;
import com.example.pharmacy.dto.MedicineUpdateDto;

 

@Service
public interface MedicineService {

	public Collection<com.example.pharmacy.dto.MedicineDto> getMedicinesFromIds(MedicineIdsDto body);
	public boolean updateMedicines(MedicineUpdateDto body, Integer id);
}
