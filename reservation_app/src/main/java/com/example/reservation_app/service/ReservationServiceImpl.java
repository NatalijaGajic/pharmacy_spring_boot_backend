package com.example.reservation_app.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.reservation_app.dto.ReservationDTO;
import com.example.reservation_app.dto.ReservationDetailsDTO;
import com.example.reservation_app.dto.ReservationMedicineDto;
import com.example.reservation_app.jdbc_repository.ReservationJdbcRepository;
import com.example.reservation_app.model.Reservation;
import com.example.reservation_app.utils.ReservationMedicineService;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationJdbcRepository reservationRepository; 
	
	@Autowired
	private ReservationMedicineService reservationMedicineService;
	
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
	public ReservationDetailsDTO findReservationDetailsById(Integer id) throws Exception {
		Reservation res = findById(id);
		Collection<ReservationMedicineDto> medicines = reservationMedicineService.getReservationMedicine(id);
		ReservationDTO resDto = mapReservationToReservationDto(res);
		ReservationDetailsDTO dto = new ReservationDetailsDTO(resDto, medicines);
		return dto;
		
	}
	
	private ReservationDTO mapReservationToReservationDto(Reservation res) {
		ReservationDTO dto = new ReservationDTO(res.getId(), res.getReservationCode(), res.getDateOfReservation(), res.getDateOfPickUp(), res.getPrice(), res.isCancelled(), res.getStatus());
		//GET CLIENT
		return dto;
	}

	@Override
	public Reservation findById(Integer id) throws Exception {
		Reservation res = reservationRepository.findById(id);
		return res;
		
	}
	
	@Override
	public Collection<Reservation> findAllReservations(Date dateOfReservation, Date dateOfPickUp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ReservationDTO> findByClient(Integer clientId) {
		Collection<Reservation> reservations = reservationRepository.getReservationsForClient(clientId);
		Collection<ReservationDTO> dtos = new ArrayList<ReservationDTO>();
		for(Reservation r: reservations) {
			dtos.add(mapReservationToReservationDto(r));
		}
		return dtos;
	}

}
