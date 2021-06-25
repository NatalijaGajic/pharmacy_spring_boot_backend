package com.example.reservation_app.jdbc_repository;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import com.example.reservation_app.model.Reservation;

@Repository
public class ReservationJdbcRepositoryImpl implements ReservationJdbcRepository{

	@Override
	public void save(Reservation reservation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Reservation findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Reservation> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Reservation reservation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Reservation getReservationsForClient(Integer clientId) {
		// TODO Auto-generated method stub
		return null;
	}

}
