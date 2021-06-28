package com.example.pharmacy.contoller;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pharmacy.dto.ReservationCreationDto;
import com.example.pharmacy.dto.ReservationUpdateDto;
import com.example.pharmacy.jpa.Client;
import com.example.pharmacy.jpa.Reservation;
import com.example.pharmacy.repository.ClientRepository;
import com.example.pharmacy.service.ReservationService;
import com.example.pharmacy.utils.Mapper;

@RestController
@EnableTransactionManagement
public class ReservationController {
	
	//TODO: try catch
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private Mapper mapper;
	
	
	@GetMapping("reservations")
	private ResponseEntity<?>  getReservations(@RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date dateOfReservation, @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date dateOfPickUp){
		try {
			Collection<Reservation> reservations = reservationService.findAllReservations(dateOfReservation, dateOfPickUp);
			return new ResponseEntity<>(reservations, HttpStatus.OK);
		} catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@GetMapping("reservations/date-of-reservation")
	private ResponseEntity<?> getReservationsBetweenDatesOfReservation(@RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date dateOfReservationStart, @RequestParam(required = false)  @DateTimeFormat(pattern="yyyy-MM-dd") Date dateOfReservationEnd){
		try {
			Collection<Reservation> reservations = reservationService.findAllByDateOfReservationBetween(dateOfReservationStart, dateOfReservationEnd);
			return new ResponseEntity<>(reservations, HttpStatus.OK);
		} catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}
	
	@GetMapping("reservations/date-of-pick-up")
	private ResponseEntity<?> getReservationsBetweenDatesOfPickUp(@RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date dateOfPickUpStart, @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date dateOfPickUpEnd){
		try {
			Collection<Reservation> reservations =  reservationService.findAllByDateOfReservationBetween(dateOfPickUpStart, dateOfPickUpEnd);
			return new ResponseEntity<>(reservations, HttpStatus.OK);
		} catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

		}
		
	}
	
	@GetMapping("reservations/client/{id}")
	private ResponseEntity<?> getReservationsByClientId(@PathVariable Integer id){
		try {
			Client client = clientRepository.findById(id).get();
			if(client != null) {
				return new ResponseEntity<>(reservationService.findByClient(client), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@PostMapping("reservations")
	private ResponseEntity createReservation(@RequestBody ReservationCreationDto reservationCreation){
		
		try {
			//TODO if client null
			Client client = clientRepository.getOne(reservationCreation.getClientId());
			if(client != null) {
				Reservation reservation = mapper.convertReservationCreationDtoToReservation(reservationCreation);
				reservation.setClient(client);
				reservationService.createReservation(reservation, reservationCreation.getMedicines());

				return new ResponseEntity(HttpStatus.CREATED);
			}
			return new ResponseEntity(HttpStatus.CONFLICT);
		}
		catch(Exception e){
			//TODO: check exception type 
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
	}
	
	@GetMapping("reservations/{id}")
	private ResponseEntity<?> getReservationById(@PathVariable Integer id) {
		try {
			Reservation reservation = reservationService.findById(id);
			return new ResponseEntity<>(reservation, HttpStatus.OK);
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

	
}
