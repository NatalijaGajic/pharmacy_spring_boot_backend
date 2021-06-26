package com.example.reservation_app.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


public class Reservation {

	private Integer id;
	
	private UUID reservationCode;
	
	private Date dateOfReservation;
	
	private Date dateOfPickUp;
	
	private double price;
	
	private boolean isCancelled;
	
	private String status;
	
	private Integer clientId;

	public Reservation(Integer id, Date dateOfReservation, Date dateOfPickUp, boolean isCancelled, 
			String status, Integer clientId, UUID reservationCode) {
		super();
		this.id = id;
		this.dateOfReservation = dateOfReservation;
		this.dateOfPickUp = dateOfPickUp;
		this.isCancelled = isCancelled;
		this.status = status;
		this.clientId = clientId;
		this.reservationCode = reservationCode;
	}
	
	public Reservation() {
		
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UUID getReservationCode() {
		return reservationCode;
	}

	public void setReservationCode(UUID reservationCode) {
		this.reservationCode = reservationCode;
	}

	public Date getDateOfReservation() {
		return dateOfReservation;
	}

	public void setDateOfReservation(Date dateOfReservation) {
		this.dateOfReservation = dateOfReservation;
	}

	public Date getDateOfPickUp() {
		return dateOfPickUp;
	}

	public void setDateOfPickUp(Date dateOfPickUp) {
		this.dateOfPickUp = dateOfPickUp;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isCancelled() {
		return isCancelled;
	}

	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	

}
