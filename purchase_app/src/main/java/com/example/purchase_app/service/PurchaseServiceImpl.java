package com.example.purchase_app.service;


import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pharmacy.dto.PharmacistDto;
import com.example.pharmacy.dto.ReservationDto;
import com.example.purchase_app.dto.PurchaseDTO;
import com.example.purchase_app.jdbc_repository.PurchaseJdbcRepository;
import com.example.purchase_app.model.Purchase;
import com.example.purchase_app.util.ReservationService;
import com.example.purchase_app.util.UserService;

@Service
public class PurchaseServiceImpl implements PurchaseService{

	@Autowired
	private PurchaseJdbcRepository purchaseRepository;

	@Autowired
	private UserService userService;
	
	@Autowired
	private ReservationService reservationService;

	@Override
	public void createPurchase(Purchase purchase) {
		purchaseRepository.save(purchase);
	}

	@Override
	public Collection<PurchaseDTO> getAll() {
		Collection<Purchase> purchases = purchaseRepository.findAll();
		Collection<PurchaseDTO> purchaseDTOs = new ArrayList<PurchaseDTO>();
		
		for(Purchase purchase: purchases) {
			Integer pharmacistId = purchase.getPharmacistId();
			Integer reservationId = purchase.getReservationId();
			PharmacistDto pharmacist = this.userService.getPharmacistsById(pharmacistId);
			ReservationDto reservation = this.reservationService.getReservationById(reservationId);
			
			PurchaseDTO purchaseDTO = new PurchaseDTO();
			purchaseDTO.setId(purchase.getId());
			purchaseDTO.setDateOfPurchase(purchase.getDateOfPurchase());
			purchaseDTO.setPaymentType(purchase.getPaymentType());
			purchaseDTO.setPharmacist(pharmacist);
			purchaseDTO.setReservation(reservation);
			purchaseDTOs.add(purchaseDTO);
		}
		
		return purchaseDTOs;
	}

	@Override
	public PurchaseDTO getByPurchaseId(Integer id) {
		Purchase purchase = purchaseRepository.findByPurchaseId(id);
		PurchaseDTO purchaseDTO = new PurchaseDTO();
		Integer pharmacistId = purchase.getPharmacistId();
		Integer reservationId = purchase.getReservationId();
		PharmacistDto pharmacist = this.userService.getPharmacistsById(pharmacistId);
		ReservationDto reservation = this.reservationService.getReservationById(reservationId);
		
		purchaseDTO.setId(purchase.getId());
		purchaseDTO.setDateOfPurchase(purchase.getDateOfPurchase());
		purchaseDTO.setPaymentType(purchase.getPaymentType());
		purchaseDTO.setPharmacist(pharmacist);
		purchaseDTO.setReservation(reservation);
		return purchaseDTO;
	}

	@Override
	public PurchaseDTO getByReservationId(Integer id) {
		Purchase purchase = purchaseRepository.findByReservationId(id);
		PurchaseDTO purchaseDTO = new PurchaseDTO();
		Integer pharmacistId = purchase.getPharmacistId();
		Integer reservationId = purchase.getReservationId();
		PharmacistDto pharmacist = this.userService.getPharmacistsById(pharmacistId);
		ReservationDto reservation = this.reservationService.getReservationById(reservationId);
		
		purchaseDTO.setId(purchase.getId());
		purchaseDTO.setDateOfPurchase(purchase.getDateOfPurchase());
		purchaseDTO.setPaymentType(purchase.getPaymentType());
		purchaseDTO.setPharmacist(pharmacist);
		purchaseDTO.setReservation(reservation);
		return purchaseDTO;
	}

	@Override
	public void delete(Integer id) {
		purchaseRepository.delete(id);
	}

}
