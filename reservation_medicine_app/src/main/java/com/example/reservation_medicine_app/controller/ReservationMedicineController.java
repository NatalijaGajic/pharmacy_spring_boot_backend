package com.example.reservation_medicine_app.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.reservation_medicine_app.dto.MedicineWithAmountDto;
import com.example.reservation_medicine_app.dto.ReservationMedicineCreationDto;
import com.example.reservation_medicine_app.dto.ReservationMedicineDto;
import com.example.reservation_medicine_app.jdbc_repository.ReservationMedicineJdbcRepository;
import com.example.reservation_medicine_app.model.ReservationMedicine;
import com.example.reservation_medicine_app.service.ReservationMedicineService;

@RestController
public class ReservationMedicineController {
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ReservationMedicineJdbcRepository reservationMedicineRepository;
	
	@Autowired
	private ReservationMedicineService reservationMedicineService;
	
	@GetMapping("reservation-medicines/reservation/{id}")
	private ResponseEntity<?> getReservationMedicines(@PathVariable Integer id){
		try {
			Collection<ReservationMedicineDto> reservationMedicines = this.reservationMedicineService.getMedicinesByReservationId(id);
			return new ResponseEntity<Collection<ReservationMedicineDto>>(reservationMedicines, HttpStatus.OK);
		} catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@PostMapping("reservation-medicines")
	private ResponseEntity<?> createReservationMedicine(@RequestBody ReservationMedicineCreationDto body){
		try {
			Collection<ReservationMedicine> reservationMedicines = new ArrayList<ReservationMedicine>();
			for(MedicineWithAmountDto medicine: body.getMedicinesWithAmount()) {
				ReservationMedicine rm = new ReservationMedicine(null, medicine.getAmount(), medicine.getMedicineId(), body.getReservationId());
				reservationMedicines.add(rm);
			}
			reservationMedicineService.createReservationMedicine(reservationMedicines);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}
}
