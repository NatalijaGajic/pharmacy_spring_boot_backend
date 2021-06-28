package com.example.purchase_app.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class PurchaseDTO {
	private Integer id;
	private Date dateOfPurchase;
	private String paymentType;
	private PharmacistDTO pharmacist;
	private ReservationDTO reservation;
	
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
	public PharmacistDTO getPharmacist() {
		return pharmacist;
	}
	public void setPharmacist(PharmacistDTO pharmacist) {
		this.pharmacist = pharmacist;
	}
	public ReservationDTO getReservation() {
		return reservation;
	}
	public void setReservation(ReservationDTO reservation) {
		this.reservation = reservation;
	}
}
