package com.example.pharmacy.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pharmacy.jpa.*;

public interface ReservationMedicineRepository extends JpaRepository<ReservationMedicine, Integer>{

	Collection<ReservationMedicine> findByMedicine(Medicine medicine);
	Collection<ReservationMedicine> findByReservation(Reservation reservation);

}
