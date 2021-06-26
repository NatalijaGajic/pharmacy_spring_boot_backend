package com.example.reservation_medicine_app.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.example.reservation_medicine_app.dto.MedicineDto;
import com.example.reservation_medicine_app.dto.ReservationMedicineDto;
import com.example.reservation_medicine_app.model.ReservationMedicine;

@Service
public interface ReservationMedicineService {

	public Collection<ReservationMedicineDto> getMedicinesByReservationId(Integer id) throws Exception;
	public void createReservationMedicine(Collection<ReservationMedicine> reservationMedicine) throws Exception;
}
