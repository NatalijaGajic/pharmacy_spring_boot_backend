package com.example.reservation_app.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pharmacy.dto.ClientDto;
import com.example.pharmacy.dto.MedicineDto;
import com.example.pharmacy.dto.MedicineIdsDto;
import com.example.pharmacy.dto.MedicineUpdateDto;
import com.example.pharmacy.dto.ReservationMedicineDto;
import com.example.reservation_app.dto.MedicineWithAmountDto;
import com.example.reservation_app.dto.ReservationDetailsDTO;
import com.example.reservation_app.dto.ReservationDto;
import com.example.reservation_app.dto.ReservationMedicineCreationDto;
import com.example.reservation_app.jdbc_repository.ReservationJdbcRepository;
import com.example.reservation_app.model.Reservation;
import com.example.reservation_app.utils.ClientService;
import com.example.reservation_app.utils.Mapper;
import com.example.reservation_app.utils.MedicineService;
import com.example.reservation_app.utils.ReservationMedicineService;
import com.example.reservation_app.utils.UserAlergiesService;
import com.example.user_alergies_app.dto.UserAlergiesDto;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationJdbcRepository reservationRepository; 
	
	@Autowired
	private ReservationMedicineService reservationMedicineService;
	
	@Autowired
	private ClientService clientService; 
	
	@Autowired
	private MedicineService medicineService; 
	
	@Autowired
	private UserAlergiesService userAlergiesService; 
	
	@Autowired
	private Mapper customMapper; 
	
	@Override
	public Collection<ReservationDto> findAllByDateOfReservation(Date dateOfReservation) {
		// TODO Auto-generated method stub
				return null;
	}

	@Override
	public Collection<ReservationDto> findAllByDateOfReservationBetween(Date dateOfReservationStart,
			Date dateOfReservationEnd) {
		Collection<Reservation> reservations = reservationRepository.findAllByDateOfReservationBetween(dateOfReservationStart, dateOfReservationEnd);
		return customMapper.mapReservationCollectionToReservationDTOCollection(reservations);
	}

	@Override
	public Collection<ReservationDto> findAllByDateOfPickUp(Date dateOfPickUp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ReservationDto> findAllByDateOfPickUpBetween(Date dateOfPickUpStart, Date dateOfPickUpEnd) {
		Collection<Reservation> reservations = reservationRepository.findAllByDateOfPickUpBetween(dateOfPickUpStart, dateOfPickUpEnd);
		return customMapper.mapReservationCollectionToReservationDTOCollection(reservations);
	}


	@Override
	public Reservation createReservation(Reservation reservation, List<MedicineWithAmountDto> medicinesWithAmount) throws Exception{
		Integer clientId = reservation.getClientId();
		ClientDto client = clientService.getClientById(clientId);
		if(client == null) {
			throw new Exception("Conflict");
		}
		if(client.getNumberOfPenalties() == 3) {
			throw new Exception("3 penalties");
		}
		if(medicinesWithAmount.isEmpty()){
			throw new Exception("There must be medicines");
		}
		MedicineIdsDto medicineIdsDto = new MedicineIdsDto();
		medicineIdsDto.setMedicineIds(new ArrayList<Integer>());
		for(MedicineWithAmountDto m: medicinesWithAmount) {
			medicineIdsDto.getMedicineIds().add(m.getMedicineId());
		}
		Collection<MedicineDto> meds = medicineService.getMedicinesFromIds(medicineIdsDto);
		if(checkIfAlergic(userAlergiesService.getUserAlergiesForUser(clientId), meds)) {
			throw new Exception("Client is alergic");
		}
		if(!checkIfEnoughQuantity(meds, medicinesWithAmount)) {
			throw new Exception("There is not enough quantity");
		}
		reservation.setPrice(calculatePrice(meds, medicinesWithAmount));
		Reservation createdReservation  = reservationRepository.save(reservation);
		setMedicinesForReservation(createdReservation, meds, medicinesWithAmount);
		return createdReservation;
		
	}

	@Override
	public Reservation cancelReservation(Reservation reservation) throws Exception {
		if(reservation.getStatus().equalsIgnoreCase("cancelled")) {
			throw new Exception("Reservation is already cancelled");
		}
		Date dateOfPickup = reservation.getDateOfPickUp();
		Date currentDate = new Date();
		long diffInMillies = Math.abs(dateOfPickup.getTime() - currentDate.getTime());
		long diff = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		if(diff < 24) {
			ClientDto client = clientService.getClientById(reservation.getClientId());
			if(client == null) {
				throw new Exception("Client not found");
			}
			int numberOfPenalties = client.getNumberOfPenalties();
			if(numberOfPenalties < 3) {
				client.setNumberOfPenalties(numberOfPenalties + 1);
			}
		}
		Collection<ReservationMedicineDto> reservationMedicines = reservationMedicineService.getReservationMedicine(reservation.getId());
		addAmountToMedicines(reservationMedicines);
		reservation.setStatus("Cancelled");
		return reservationRepository.save(reservation);
	}

	@Override
	public Reservation updateReservationStatus(Reservation reservation, String status) throws Exception {
		if(status.equalsIgnoreCase("cancelled")) {
			return cancelReservation(reservation);
		}else if(status.equalsIgnoreCase("picked-up")) {
			reservation.setStatus(status);
			return reservationRepository.save(reservation);
		}
		else {
			throw new Exception("Invalid status");
		}
	}
	
	@Override
	public ReservationDetailsDTO findReservationDetailsById(Integer id) throws Exception {
		Reservation res = findById(id);
		Collection<ReservationMedicineDto> medicines = reservationMedicineService.getReservationMedicine(id);
		ReservationDto resDto = customMapper.mapReservationToReservationDto(res);
		ReservationDetailsDTO dto = new ReservationDetailsDTO(resDto, medicines);
		return dto;
		
	}
	

	@Override
	public Reservation findById(Integer id) throws Exception {
		Reservation res = reservationRepository.findById(id);
		return res;
		
	}
	
	@Override
	public Collection<ReservationDto> findAllReservations(Date dateOfReservation, Date dateOfPickUp) {
		Collection<Reservation> reservations;
		if(dateOfReservation != null) {
			reservations = reservationRepository.findAllByDateOfReservation(dateOfReservation);
			return customMapper.mapReservationCollectionToReservationDTOCollection(reservations);
		}
		if(dateOfPickUp != null) {
			reservations = reservationRepository.findAllByDateOfPickUp(dateOfPickUp);
			return customMapper.mapReservationCollectionToReservationDTOCollection(reservations);
		}
		reservations = reservationRepository.findAll();
		return customMapper.mapReservationCollectionToReservationDTOCollection(reservations);

	}

	@Override
	public Collection<ReservationDto> findByClient(Integer clientId) {
		Collection<Reservation> reservations = reservationRepository.getReservationsForClient(clientId);
		return customMapper.mapReservationCollectionToReservationDTOCollection(reservations);

	}

	private boolean checkIfAlergic(Collection<UserAlergiesDto> alergyMedicines, Collection<MedicineDto> reservationMedicine) {
		if(alergyMedicines == null) {
			return false;
		}
		else if(alergyMedicines.size() == 0) {
			return false;
		}
		ArrayList<Integer> arrayOfMedicineIds = new ArrayList<>();
		for(UserAlergiesDto m: alergyMedicines) {
			arrayOfMedicineIds.add(m.getMedicine().getId());
		}
		for(MedicineDto m: reservationMedicine) {
			if(arrayOfMedicineIds.contains(m.getId())) {
				return true;
			}
		}
		return false;
	}
	
	private boolean checkIfEnoughQuantity(Collection<MedicineDto> medicines, List<MedicineWithAmountDto> reservationMedicines) {
		int index = 0;
		for(MedicineDto m: medicines) {
			if(m.getQuantity() < reservationMedicines.get(index).getAmount()) {
				return false;
			}
			index++;
		}
		return true;
	}
	
	private void setMedicinesForReservation(Reservation createdReservation, Collection<MedicineDto> meds,
			List<MedicineWithAmountDto> medicinesWithAmount) {
		Integer index = 0;
		for(MedicineDto med: meds) {
			Integer newQuantity = med.getQuantity() - medicinesWithAmount.get(index).getAmount();
			MedicineUpdateDto updateDto = new MedicineUpdateDto(med.getName(), med.getPrice(), med.getManufacturer(), med.getDescription(), newQuantity);
			index++;
			medicineService.updateMedicines(updateDto, med.getId());
		}
		ReservationMedicineCreationDto creationDto = new ReservationMedicineCreationDto(createdReservation.getId(), medicinesWithAmount);
		reservationMedicineService.createReservationMedicine(creationDto);
	}
	

	private double calculatePrice(Collection<MedicineDto> meds, List<MedicineWithAmountDto> medicinesWithAmount) {
		Integer index = 0;
		double price = 0;
		for(MedicineDto med: meds) {
			price = price + (med.getPrice()*medicinesWithAmount.get(index).getAmount());
			index++;
		}
		return price;
	}
	
	private void addAmountToMedicines(Collection<ReservationMedicineDto> reservationMedicines) {
		for(ReservationMedicineDto rm: reservationMedicines) {
			Integer newQuantity = rm.getMedicine().getQuantity() + rm.getAmount();
			rm.getMedicine().setQuantity(newQuantity);
			MedicineUpdateDto updateDto = customMapper.mapMedicineDtoToMedicineUpdateDto(rm.getMedicine());
			medicineService.updateMedicines(updateDto, rm.getMedicine().getId());
		}
		
	}

	@Override
	public void deleteReservation(Reservation res) {
		reservationRepository.delete(res);
		
	}
}
