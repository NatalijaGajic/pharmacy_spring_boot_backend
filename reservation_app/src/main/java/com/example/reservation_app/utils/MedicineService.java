package com.example.reservation_app.utils;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.example.reservation_app.dto.MedicineDTO;


@Service
public interface MedicineService {

	public Collection<MedicineDTO> getMedicinesFromIds();
	public void updateMedicines();
}
