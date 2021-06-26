package com.example.reservation_medicine_app.jdbc_repository;

import java.util.Collection;

import com.example.reservation_medicine_app.model.ReservationMedicine;

public interface ReservationMedicineJdbcRepository {

	public void save(ReservationMedicine reservationMedicine);
	public Collection<ReservationMedicine> findByReservationId(Integer id);
}
