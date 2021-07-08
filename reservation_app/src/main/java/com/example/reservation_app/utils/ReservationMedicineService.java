package com.example.reservation_app.utils;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.example.pharmacy.dto.ReservationMedicineDto;
import com.example.reservation_app.dto.ReservationMedicineCreationDto;

@Service
public interface ReservationMedicineService {

	public Collection<ReservationMedicineDto> getReservationMedicine(Integer reservationId);
	public boolean createReservationMedicine(ReservationMedicineCreationDto body);
}
