package com.example.reservation_app.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.reservation_app.dto.MedicineWithAmountDto;
import com.example.reservation_app.dto.ReservationDetailsDTO;
import com.example.reservation_app.dto.ReservationDto;
import com.example.reservation_app.model.Reservation;
@Service
public interface ReservationService {
	
	Collection<ReservationDto> findAllByDateOfReservation(Date dateOfReservation);
	Collection<ReservationDto> findAllByDateOfReservationBetween(Date dateOfReservationStart, Date dateOfReservationEnd);
	Collection<ReservationDto> findAllByDateOfPickUp(Date dateOfPickUp);
	Collection<ReservationDto> findAllByDateOfPickUpBetween(Date dateOfPickUpStart, Date dateOfPickUpEnd);
	Collection<ReservationDto> findByClient(Integer clientId);
	Reservation createReservation(Reservation reservation, List<MedicineWithAmountDto> medicines) throws Exception;
	Reservation cancelReservation(Reservation  reservation) throws Exception;
	Reservation updateReservationStatus(Reservation  reservation, String status) throws Exception;
	Reservation findById(Integer id) throws Exception;
	ReservationDetailsDTO findReservationDetailsById(Integer id) throws Exception;
	Collection<ReservationDto> findAllReservations(Date dateOfReservation, Date dateOfPickUp);
	void deleteReservation(Reservation res);
}
