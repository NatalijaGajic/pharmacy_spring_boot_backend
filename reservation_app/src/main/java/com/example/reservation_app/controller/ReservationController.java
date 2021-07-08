package com.example.reservation_app.controller;

import java.util.Collection;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pharmacy.dto.ReservationUpdateDto;
import com.example.reservation_app.dto.ReservationCreationDto;
import com.example.reservation_app.dto.ReservationDetailsDTO;
import com.example.reservation_app.dto.ReservationDto;
import com.example.reservation_app.jdbc_repository.ReservationJdbcRepository;
import com.example.reservation_app.model.Reservation;
import com.example.reservation_app.service.ReservationService;
import com.example.reservation_app.utils.Mapper;

@RestController
public class ReservationController {

	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private ReservationJdbcRepository reservationRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private Mapper customMapper;
	
	@GetMapping("reservations")
	private ResponseEntity<?> getReservations(@RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date dateOfReservation, @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date dateOfPickUp){
		try {
			Collection<ReservationDto> response = reservationService.findAllReservations(dateOfReservation, dateOfPickUp);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@GetMapping("reservations/date-of-reservation")
	private ResponseEntity<?> getReservationsBetweenDatesOfReservation(@RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date dateOfReservationStart, @RequestParam(required = false)  @DateTimeFormat(pattern="yyyy-MM-dd") Date dateOfReservationEnd){
		try {
			Collection<ReservationDto> response = reservationService.findAllByDateOfReservationBetween(dateOfReservationStart, dateOfReservationEnd);;
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@GetMapping("reservations/date-of-pick-up")
	private ResponseEntity<?> getReservationsBetweenDatesOfPickUp(@RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date dateOfPickUpStart, @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date dateOfPickUpEnd){
		try {
			Collection<ReservationDto> response = reservationService.findAllByDateOfReservationBetween(dateOfPickUpStart, dateOfPickUpEnd);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
	}

	@GetMapping("reservations/client/{id}")
	private ResponseEntity<?> getReservationsByClientId(@PathVariable Integer id){
		try {
			//TODO: check client
			Collection<ReservationDto> response = reservationService.findByClient(id);
			if(response.size() > 0) {
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
			return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
	}
	
	@PostMapping("reservations")
	private ResponseEntity createReservation(@RequestBody ReservationCreationDto reservationCreation){
		
		try {
				Reservation reservation = customMapper.mapReservationCreationDtoToReservation(reservationCreation);
				Reservation createdReservation = reservationService.createReservation(reservation, reservationCreation.getMedicines());
				ReservationDto  dto = customMapper.mapReservationToReservationDto(createdReservation);
				return ResponseEntity.status(HttpStatus.CREATED).body(dto);
		}
		catch(Exception e){
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
	}
	
	@GetMapping("reservations/{id}")
	private ResponseEntity<?> getReservationById(@PathVariable Integer id) {
		try {
			ReservationDetailsDTO reservation = reservationService.findReservationDetailsById(id);
			return new ResponseEntity<ReservationDetailsDTO>(reservation, HttpStatus.OK);
		}catch(Exception e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@PutMapping("reservations/{id}")
	private ResponseEntity<?> updateReservation(@PathVariable Integer id, @RequestBody ReservationUpdateDto statusDto) {
		try {
			Reservation reservation = reservationService.findById(id);
			reservationService.updateReservationStatus(reservation, statusDto.getStatus());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(Exception e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@DeleteMapping("reservations/{id}")
	private ResponseEntity<?> deleteReservation(@PathVariable Integer id) {
		try {
			Reservation reservation = reservationService.findById(id);
			if(reservation == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			reservationService.deleteReservation(reservation);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(Exception e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}
