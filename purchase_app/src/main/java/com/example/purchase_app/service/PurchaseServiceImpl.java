package com.example.purchase_app.service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.purchase_app.dto.PharmacistDTO;
import com.example.purchase_app.dto.PurchaseDTO;
import com.example.purchase_app.dto.ReservationDTO;
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
		// TODO Auto-generated method stub
	}

	@Override
	public Collection<PurchaseDTO> getAll() {
		Collection<Purchase> purchases = purchaseRepository.findAll();
		Collection<PurchaseDTO> purchaseDTOs = new ArrayList<PurchaseDTO>();
		
		for(Purchase purchase: purchases) {
			Integer pharmacistId = purchase.getPharmacistId();
			Integer reservationId = purchase.getReservationId();
			PharmacistDTO pharmacist = this.userService.getPharmacistsById(pharmacistId);
			ReservationDTO reservation = this.reservationService.getReservationById(reservationId);
			
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PurchaseDTO getByReservationId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
