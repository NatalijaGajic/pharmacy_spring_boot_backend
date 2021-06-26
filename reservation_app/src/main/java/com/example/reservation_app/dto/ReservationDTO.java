package com.example.reservation_app.dto;

import java.util.Date;
import java.util.UUID;

public class ReservationDTO {

	private Integer id;
	
	private UUID reservationCode;
	
	private Date dateOfReservation;
	
	private Date dateOfPickUp;
	
	private double price;
	
	private boolean isCancelled;
	
	private String status;
	
	private ClientDTO client;
	
	

	public ReservationDTO(Integer id, UUID reservationCode, Date dateOfReservation, Date dateOfPickUp, double price,
			boolean isCancelled, String status) {
		super();
		this.id = id;
		this.reservationCode = reservationCode;
		this.dateOfReservation = dateOfReservation;
		this.dateOfPickUp = dateOfPickUp;
		this.price = price;
		this.isCancelled = isCancelled;
		this.status = status;
	}

	public ClientDTO getClient() {
		return client;
	}

	public void setClient(ClientDTO client) {
		this.client = client;
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

	

}
