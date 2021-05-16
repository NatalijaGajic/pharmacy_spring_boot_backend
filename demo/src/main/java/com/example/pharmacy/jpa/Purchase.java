package com.example.pharmacy.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Purchase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = false)
	private Date dateOfPurchase;
	
	
	@Column(nullable = false)
	private String paymentType;
	
	@ManyToOne
	@JoinColumn(nullable=true)
	private Pharmacist pharmacist;
	
	//TODO check if okay 0..1 0..1
	@ManyToOne
	private Reservation reservation;

	public Purchase(Date dateOfPurchase, String paymentType, Pharmacist pharmacist,
			Reservation reservation) {
		super();
		this.dateOfPurchase = dateOfPurchase;
		this.paymentType = paymentType;
		this.pharmacist = pharmacist;
		this.reservation = reservation;
	}

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

	public Pharmacist getPharmacist() {
		return pharmacist;
	}

	public void setPharmacist(Pharmacist pharmacist) {
		this.pharmacist = pharmacist;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	
	
}
