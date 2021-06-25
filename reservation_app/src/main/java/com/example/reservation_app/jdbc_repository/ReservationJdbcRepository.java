package com.example.reservation_app.jdbc_repository;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import com.example.reservation_app.model.Reservation;


@Repository
public interface ReservationJdbcRepository {
	public void save(Reservation reservation);
	public Reservation findById(Integer id);
	public Collection<Reservation> findAll();
	public void delete(Reservation reservation);
	public Collection<Reservation> getReservationsForClient(Integer clientId);
}
