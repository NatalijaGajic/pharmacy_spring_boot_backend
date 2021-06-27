package com.example.reservation_app.jdbc_repository;

import java.util.Collection;
import java.util.Date;

import org.springframework.stereotype.Repository;

import com.example.reservation_app.model.Reservation;


@Repository
public interface ReservationJdbcRepository {
	public Reservation save(Reservation reservation);
	public Reservation findById(Integer id);
	public Collection<Reservation> findAll();
	public void delete(Reservation reservation);
	public Collection<Reservation> getReservationsForClient(Integer clientId);
	Collection<Reservation> findAllByDateOfReservationBetween(Date dateOfReservationStart, Date dateOfReservationEnd);
	Collection<Reservation> findAllByDateOfPickUpBetween(Date dateOfPickUpStart, Date dateOfPickUpEnd);	
	Collection<Reservation> findAllByDateOfReservation(Date dateOfReservation);
	Collection<Reservation> findAllByDateOfPickUp(Date dateOfPickUp);
}
