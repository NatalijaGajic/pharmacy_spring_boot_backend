package com.example.pharmacy.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pharmacy.dto.ReservationMedicineDto;
import com.example.pharmacy.jpa.Client;
import com.example.pharmacy.jpa.Medicine;
import com.example.pharmacy.jpa.Reservation;
import com.example.pharmacy.jpa.ReservationMedicine;
import com.example.pharmacy.repository.ClientRepository;
import com.example.pharmacy.repository.MedicineRepository;
import com.example.pharmacy.repository.ReservationMedicineRepository;
import com.example.pharmacy.repository.ReservationRepository;

@Service
public class ReservationService implements IReservationService{

	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private MedicineRepository medicineRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ReservationMedicineRepository reservationMedicineRepository;
	
	@Override
	public Collection<Reservation> findAllByDateOfReservation(Date dateOfReservation) {
		return reservationRepository.findAllByDateOfReservation(dateOfReservation);
	}

	@Override
	public Collection<Reservation> findAllByDateOfReservationBetween(Date dateOfReservationStart,
			Date dateOfReservationEnd) {
		return reservationRepository.findAllByDateOfReservationBetween(dateOfReservationStart, dateOfReservationEnd);
	}

	@Override
	public Collection<Reservation> findAllByDateOfPickUp(Date dateOfPickUp) {
		return reservationRepository.findAllByDateOfPickUp(dateOfPickUp);

	}

	@Override
	public Collection<Reservation> findAllByDateOfPickUpBetween(Date dateOfPickUpStart, Date dateOfPickUpEnd) {
		return reservationRepository.findAllByDateOfPickUpBetween(dateOfPickUpStart, dateOfPickUpEnd);

	}

	@Override
	public Collection<Reservation> findByClient(Client client) {
		return reservationRepository.findByClient(client);
	}

	@Transactional
	@Override
	public Reservation createReservation(Reservation reservation,  List<ReservationMedicineDto> medicinesIdList) throws Exception {
		Client client = reservation.getClient();
		if(client.getNumberOfPenalties() == 3) {
			//TODO: make custom exceptions, check if == 3 or > 3
			throw new Exception("Number of penalties is 3");
		}
		if(medicinesIdList.size() == 0) {
			throw new Exception("Reservation must have medicines");
		}
		List<Medicine> medicines = getMedicinesFromIds(medicinesIdList);
		if(checkIfAlergic(client.getAlergy_medicines(), medicines)) {
			throw new Exception("Client is alergic");
		}
		if(!checkIfEnoughQuantity(medicinesIdList)) {
			throw new Exception("There is not enough quantity");
		}
		//Add medicines and update their amount
		Reservation createdReservation = reservationRepository.save(reservation);
		setMedicinesForReservation(createdReservation, medicines, medicinesIdList);
		//TODO: Set reservation status
		//reservationRepository.save(createdReservation);
		return createdReservation; 
	}

	@Transactional
	@Override
	public Reservation cancelReservation(Reservation reservation) throws Exception{
		if(reservation.getStatus().equals("cancelled")) {
			//TODO: custom exception
			throw new Exception("Reservation is already cancelled");
		}
		Date dateOfPickup = reservation.getDateOfPickUp();
		Date currentDate = new Date();
		long diffInMillies = Math.abs(dateOfPickup.getTime() - currentDate.getTime());
		long diff = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		if(diff < 24) {
			Client client = clientRepository.findById(reservation.getClient().getId()).get();
			int numberOfPenalties = client.getNumberOfPenalties();
			if(numberOfPenalties < 3) {
				client.setNumberOfPenalties(numberOfPenalties + 1);
			}
		}
		addAmountToMedicines(reservation.getReservationMedicines());
		reservation.setStatus("cancelled");
		return reservationRepository.save(reservation);
	}

	//Reservations can't be deleted, only cancelled
	
	private boolean checkIfAlergic(List<Medicine> alergyMedicines, List<Medicine> reservationMedicine) {
		if(alergyMedicines == null) {
			return false;
		}
		else if(alergyMedicines.size() == 0) {
			return false;
		}
		//Problem with quantity, check for Ids
		ArrayList<Integer> arrayOfMedicineIds = new ArrayList<>();
		for(Medicine m: alergyMedicines) {
			arrayOfMedicineIds.add(m.getId());
		}
		for(Medicine m: reservationMedicine) {
			if(arrayOfMedicineIds.contains(m.getId())) {
				return true;
			}
		}
		return false;
	}

	private boolean checkIfEnoughQuantity(List<ReservationMedicineDto> reservationMedicines) {
		for(ReservationMedicineDto rm: reservationMedicines) {
			Medicine medicine = medicineRepository.findById(rm.getId()).get();
			if(medicine.getQuantity() < rm.getAmount()) {
				return false;
			}
		}
		return true;
	}
	
	private void substractAmountFromMedicines(Medicine reservationMedicine, int amount) {
			int quantity = medicineRepository.findById(reservationMedicine.getId()).get().getQuantity();
			reservationMedicine.setQuantity(quantity - amount);
			medicineRepository.save(reservationMedicine);
	}
	
	private void addAmountToMedicines(List<ReservationMedicine> reservationMedicine) {
		for(ReservationMedicine rm: reservationMedicine) {
			int quantity = medicineRepository.findById(rm.getMedicine().getId()).get().getQuantity();
			rm.getMedicine().setQuantity(quantity + rm.getAmount());
			medicineRepository.save(rm.getMedicine());
		}
	}
	
	private List<Medicine> getMedicinesFromIds( List<ReservationMedicineDto> medicineIdsWithAmount){
		List<Medicine> medicines = new ArrayList<Medicine>();
		for(ReservationMedicineDto rm: medicineIdsWithAmount) {
			//TODO: should call service
			Medicine m = medicineRepository.getOne(rm.getId());
			medicines.add(m);
		}
		return medicines;
		
	}
	
	private void setMedicinesForReservation(Reservation reservation, List<Medicine> medicines, List<ReservationMedicineDto> amountsList) {
		List<ReservationMedicine> list = new ArrayList<ReservationMedicine>();
		for(int i=0;i<medicines.size();i++) {
			//TODO: work with service
			substractAmountFromMedicines(medicines.get(i), amountsList.get(i).getAmount());
			ReservationMedicine rm = new ReservationMedicine(null, amountsList.get(i).getAmount(), medicines.get(i), reservation);
			reservationMedicineRepository.save(rm);
		}
		reservation.setReservationMedicines(list);
	}

	@Override
	public Collection<Reservation> findAllReservations(Date dateOfReservation, Date dateOfPickUp) {
		if(dateOfReservation != null) {
			if(dateOfPickUp != null) {
				//TODO: intersection 
				Collection<Reservation> reservations = reservationRepository.findAllByDateOfReservation(dateOfReservation);
				reservations.retainAll(reservationRepository.findAllByDateOfPickUp(dateOfPickUp));
				return reservations;
			}
			return reservationRepository.findAllByDateOfReservation(dateOfReservation);

		} else if(dateOfPickUp != null) {
			return reservationRepository.findAllByDateOfPickUp(dateOfPickUp);
		} else {
			return reservationRepository.findAll();
		}
	}

	@Override
	public Reservation updateReservationStatus(Reservation reservation, String status) throws Exception {
		if(status.equals("cancelled")) {
			cancelReservation(reservation);
		}else if(status.equals("picked-up")) {
			//TODO 
		}
		else {
			throw new Exception("Invalid status");
		}
		return null;
	}

	@Override
	public Reservation findById(Integer id) throws Exception {
		Reservation res = reservationRepository.getOne(id);
		if(res == null) {
			//TODO: custom exception
			throw new Exception("Reservation not found");
		}
		return res;
	}
}
