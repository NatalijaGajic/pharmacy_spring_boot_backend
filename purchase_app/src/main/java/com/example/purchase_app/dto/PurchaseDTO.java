package com.example.purchase_app.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.example.pharmacy.dto.PharmacistDto;
import com.example.pharmacy.dto.ReservationDto;

public class PurchaseDTO {
	private Integer id;
	private Date dateOfPurchase;
	private String paymentType;
	private PharmacistDto pharmacist;
	private ReservationDto reservation;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDateOfPurchase() {
		return dateOfPurchase;
	}
	public void setDateOfPurchase(Date dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public PharmacistDto getPharmacist() {
		return pharmacist;
	}
	public void setPharmacist(PharmacistDto pharmacist) {
		this.pharmacist = pharmacist;
	}
	public ReservationDto getReservation() {
		return reservation;
	}
	public void setReservation(ReservationDto reservation) {
		this.reservation = reservation;
	}
}
