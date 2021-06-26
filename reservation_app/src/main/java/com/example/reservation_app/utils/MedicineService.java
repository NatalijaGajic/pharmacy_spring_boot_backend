package com.example.reservation_app.utils;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.example.reservation_app.dto.MedicineDto;


@Service
public interface MedicineService {

	public Collection<MedicineDto> getMedicinesFromIds();
	public void updateMedicines();
}
