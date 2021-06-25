package com.example.reservation_app.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.reservation_app.dto.ClientDTO;
import com.example.reservation_app.dto.ReservationMedicineDto;
import com.example.reservation_app.model.Reservation;
@Service
public interface ReservationService {
	
	Collection<Reservation> findAllByDateOfReservation(Date dateOfReservation);
	Collection<Reservation> findAllByDateOfReservationBetween(Date dateOfReservationStart, Date dateOfReservationEnd);
	Collection<Reservation> findAllByDateOfPickUp(Date dateOfPickUp);
	Collection<Reservation> findAllByDateOfPickUpBetween(Date dateOfPickUpStart, Date dateOfPickUpEnd);
	Collection<Reservation> findByClient(Integer clientId);
	Reservation createReservation(Reservation reservation, List<ReservationMedicineDto> medicines) throws Exception;
	Reservation cancelReservation(Reservation  reservation) throws Exception;
	Reservation updateReservationStatus(Reservation  reservation, String status) throws Exception;
	Reservation findById(Integer id) throws Exception;
	Collection<Reservation> findAllReservations(Date dateOfReservation, Date dateOfPickUp);
}
