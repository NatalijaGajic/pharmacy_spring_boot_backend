package com.example.pharmacy.repository;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pharmacy.jpa.Client;
import com.example.pharmacy.jpa.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer>{

	Collection<Reservation> findAllByDateOfReservation(Date dateOfReservation);
	Collection<Reservation> findAllByDateOfReservationBetween(Date dateOfReservationStart, Date dateOfReservationEnd);
	Collection<Reservation> findAllByDateOfPickUp(Date dateOfPickUp);
	Collection<Reservation> findAllByDateOfPickUpBetween(Date dateOfPickUpStart, Date dateOfPickUpEnd);
	Collection<Reservation> findByClient(Client client);
	//TODO findByMedicine
}
