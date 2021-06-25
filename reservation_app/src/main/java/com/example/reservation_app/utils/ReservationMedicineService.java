package com.example.reservation_app.utils;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.example.reservation_app.dto.MedicineDTO;

@Service
public interface ReservationMedicineService {

	public Collection<MedicineDTO> getReservationMedicine();
	public void createReservationMedicine();
}
