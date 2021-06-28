package com.example.reservation_app.utils;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.example.reservation_app.dto.MedicineDto;
import com.example.reservation_app.dto.MedicineIdsDto;
import com.example.reservation_app.dto.MedicineUpdateDto;


@Service
public interface MedicineService {

	public Collection<MedicineDto> getMedicinesFromIds(MedicineIdsDto body);
	public boolean updateMedicines(MedicineUpdateDto body, Integer id);
}
