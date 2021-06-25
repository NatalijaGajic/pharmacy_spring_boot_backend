package com.example.reservation_app.dto;

import java.util.Date;
import java.util.List;

public class ReservationCreationDTO {
	
	private Date dateOfReservation;

	private Date dateOfPickUp;
	
	private boolean isCancelled;
	
	private String status;

	private Integer clientId;
	
	private List<ReservationMedicineDto> medicines;

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

	public List<ReservationMedicineDto> getMedicines() {
		return medicines;
	}

	public void setMedicines(List<ReservationMedicineDto> medicines) {
		this.medicines = medicines;
	}
}
