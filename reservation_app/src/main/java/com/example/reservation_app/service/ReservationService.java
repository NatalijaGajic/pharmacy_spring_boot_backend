package com.example.reservation_app.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.reservation_app.dto.ClientDTO;
import com.example.reservation_app.dto.MedicineWithAmountDto;
import com.example.reservation_app.dto.ReservationDTO;
import com.example.reservation_app.dto.ReservationDetailsDTO;
import com.example.reservation_app.dto.ReservationMedicineDto;
import com.example.reservation_app.model.Reservation;
@Service
public interface ReservationService {
	
	Collection<ReservationDTO> findAllByDateOfReservation(Date dateOfReservation);
	Collection<ReservationDTO> findAllByDateOfReservationBetween(Date dateOfReservationStart, Date dateOfReservationEnd);
	Collection<ReservationDTO> findAllByDateOfPickUp(Date dateOfPickUp);
	Collection<ReservationDTO> findAllByDateOfPickUpBetween(Date dateOfPickUpStart, Date dateOfPickUpEnd);
	Collection<ReservationDTO> findByClient(Integer clientId);
	Reservation createReservation(Reservation reservation, List<MedicineWithAmountDto> medicines) throws Exception;
	Reservation cancelReservation(Reservation  reservation) throws Exception;
	Reservation updateReservationStatus(Reservation  reservation, String status) throws Exception;
	Reservation findById(Integer id) throws Exception;
	ReservationDetailsDTO findReservationDetailsById(Integer id) throws Exception;
	Collection<ReservationDTO> findAllReservations(Date dateOfReservation, Date dateOfPickUp);
}
