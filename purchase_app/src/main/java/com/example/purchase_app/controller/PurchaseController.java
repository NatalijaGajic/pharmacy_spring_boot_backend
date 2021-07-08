package com.example.purchase_app.controller;

import java.util.Collection;
import java.util.Date;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.purchase_app.dto.PurchaseCreationDto;
import com.example.purchase_app.dto.PurchaseDTO;
import com.example.purchase_app.model.Purchase;
import com.example.purchase_app.service.PurchaseService;


@RestController
public class PurchaseController {
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private PurchaseService purchaseService;
	
	@GetMapping("purchases")
	private ResponseEntity<?> getPurchases(){
		try {
			Collection<PurchaseDTO> purchases = this.purchaseService.getAll();
			return new ResponseEntity<Collection<PurchaseDTO>>(purchases, HttpStatus.OK);
		} catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@GetMapping("purchases/{id}")
	private ResponseEntity<?> getPurchases(@PathVariable Integer id){
		try {
			PurchaseDTO purchase = this.purchaseService.getByPurchaseId(id);
			return new ResponseEntity<PurchaseDTO>(purchase, HttpStatus.OK);
		} catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@GetMapping("purchases/reservations/{id}")
	private ResponseEntity<?> getPurchasesByReservationId(@PathVariable Integer id){
		try {
			PurchaseDTO purchase = this.purchaseService.getByReservationId(id);
			return new ResponseEntity<PurchaseDTO>(purchase, HttpStatus.OK);
		} catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@PostMapping("purchases")
	private ResponseEntity<?> createPurchase(@RequestBody PurchaseCreationDto body){
		try {
			Date dateOfPurchase = body.getDateOfPurchase();
			String paymentType = body.getPaymentType();
			Integer pharmacistId = body.getPharmacist().getId();
			Integer reservationId = body.getReservation().getId();
			Purchase purchase = new Purchase(null, dateOfPurchase, paymentType, pharmacistId, reservationId);
			this.purchaseService.createPurchase(purchase);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@DeleteMapping("purchases/{id}")
	private ResponseEntity deletePurchase(@PathVariable Integer id) {
		try {
			this.purchaseService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(Exception e) {
			if(e.getClass().equals(NoSuchElementException.class)) {
				return new ResponseEntity(HttpStatus.NOT_FOUND);
			}
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
