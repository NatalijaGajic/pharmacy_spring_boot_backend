package com.example.reservation_app.dto;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

public class ReservationDetailsDTO {
	
	private ReservationDTO reservation;
	
	private Collection<ReservationMedicineDto> medicines;

	
	public ReservationDTO getReservation() {
		return reservation;
	}

	public void setReservation(ReservationDTO reservation) {
		this.reservation = reservation;
	}	

	public ReservationDetailsDTO(ReservationDTO reservation,
			Collection<ReservationMedicineDto> medicines) {
		super();
		this.reservation = reservation;
		this.medicines = medicines;
		
	}

	public ReservationDetailsDTO() {
		super();
	}

	public Collection<ReservationMedicineDto> getMedicines() {
		return medicines;
	}

	public void setMedicines(Collection<ReservationMedicineDto> medicines) {
		this.medicines = medicines;
	}

	

}
