package com.example.purchase_app.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Purchase {
	private Integer id;
	private Date dateOfPurchase;
	private String paymentType;
	private Integer pharmacistId;
	private Integer reservationId;
	
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
	public Integer getPharmacistId() {
		return pharmacistId;
	}
	public void setPharmacistId(Integer pharmacistId) {
		this.pharmacistId = pharmacistId;
	}
	public Integer getReservationId() {
		return reservationId;
	}
	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}
	public Purchase(Integer id, Date dateOfPurchase, String paymentType, Integer pharmacistId, Integer reservationId) {
		super();
		this.id = id;
		this.dateOfPurchase = dateOfPurchase;
		this.paymentType = paymentType;
		this.pharmacistId = pharmacistId;
		this.reservationId = reservationId;
	}
}
