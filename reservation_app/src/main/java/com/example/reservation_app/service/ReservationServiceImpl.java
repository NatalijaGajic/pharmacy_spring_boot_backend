package com.example.reservation_app.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.reservation_app.dto.ClientDTO;
import com.example.reservation_app.dto.ReservationMedicineDto;
import com.example.reservation_app.model.Reservation;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Override
	public Collection<Reservation> findAllByDateOfReservation(Date dateOfReservation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Reservation> findAllByDateOfReservationBetween(Date dateOfReservationStart,
			Date dateOfReservationEnd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Reservation> findAllByDateOfPickUp(Date dateOfPickUp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Reservation> findAllByDateOfPickUpBetween(Date dateOfPickUpStart, Date dateOfPickUpEnd) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Reservation createReservation(Reservation reservation, List<ReservationMedicineDto> medicines)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reservation cancelReservation(Reservation reservation) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reservation updateReservationStatus(Reservation reservation, String status) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reservation findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Reservation> findAllReservations(Date dateOfReservation, Date dateOfPickUp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Reservation> findByClient(Integer clientId) {
		// TODO Auto-generated method stub
		return null;
	}

}
