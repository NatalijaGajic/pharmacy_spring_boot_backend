package com.example.reservation_app.controller;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.reservation_app.dto.ReservationCreationDTO;
import com.example.reservation_app.dto.ReservationDetailsDTO;
import com.example.reservation_app.dto.ReservationUpdateDto;
import com.example.reservation_app.jdbc_repository.ReservationJdbcRepository;
import com.example.reservation_app.model.Reservation;
import com.example.reservation_app.service.ReservationService;

@RestController
public class ReservationController {

	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private ReservationJdbcRepository reservationRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping("reservations")
	private Collection<Reservation> getReservations(@RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date dateOfReservation, @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date dateOfPickUp){
		return reservationService.findAllReservations(dateOfReservation, dateOfPickUp);
	}
	
	@GetMapping("reservations/date-of-reservation")
	private Collection<Reservation> getReservationsBetweenDatesOfReservation(@RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date dateOfReservationStart, @RequestParam(required = false)  @DateTimeFormat(pattern="yyyy-MM-dd") Date dateOfReservationEnd){
		return reservationService.findAllByDateOfReservationBetween(dateOfReservationStart, dateOfReservationEnd);
	}
	
	@GetMapping("reservations/date-of-pick-up")
	private Collection<Reservation> getReservationsBetweenDatesOfPickUp(@RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date dateOfPickUpStart, @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date dateOfPickUpEnd){
		return reservationService.findAllByDateOfReservationBetween(dateOfPickUpStart, dateOfPickUpEnd);
		
	}
	
	//TODO: make clientId query param
	@GetMapping("reservations/client/{id}")
	private ResponseEntity<?> getReservationsByClientId(@PathVariable Integer id){
		try {
			//TODO: check client
			return new ResponseEntity<>(reservationService.findByClient(id), HttpStatus.OK);
		} catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
	}
	
	@PostMapping("reservations")
	private ResponseEntity createReservation(@RequestBody ReservationCreationDTO reservationCreation){
		
		try {
				Reservation reservation = convertReservationCreationDtoToReservation(reservationCreation);
				//TODO: check client
				reservationService.createReservation(reservation, reservationCreation.getMedicines());
				return new ResponseEntity(HttpStatus.CREATED);
		}
		catch(Exception e){
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
	}
	
	//TODO: fix
	@GetMapping("reservations/{id}")
	private ResponseEntity<?> getReservationById(@PathVariable Integer id) {
		try {
			ReservationDetailsDTO reservation = reservationService.findReservationDetailsById(id);
			return new ResponseEntity<ReservationDetailsDTO>(reservation, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("reservations/{id}")
	private ResponseEntity<?> updateReservation(@PathVariable Integer id, @RequestBody ReservationUpdateDto statusDto) {
		try {
			Reservation reservation = reservationService.findById(id);
			reservationService.updateReservationStatus(reservation, statusDto.getStatus());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	private Reservation convertReservationCreationDtoToReservation(ReservationCreationDTO reservationCreation) {
		UUID code = UUID.randomUUID();
		return new Reservation(reservationCreation.getClientId(), reservationCreation.getDateOfReservation(), reservationCreation.getDateOfPickUp(), reservationCreation.isCancelled(), reservationCreation.getStatus(), reservationCreation.getClientId(), code);
	}
}
