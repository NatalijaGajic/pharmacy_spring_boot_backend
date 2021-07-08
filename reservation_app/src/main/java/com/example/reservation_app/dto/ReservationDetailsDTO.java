package com.example.reservation_app.dto;

import java.util.Collection;

import com.example.pharmacy.dto.ReservationMedicineDto;




public class ReservationDetailsDTO {
	
	private ReservationDto reservation;
	
	private Collection<ReservationMedicineDto> medicines;

	
	public ReservationDto getReservation() {
		return reservation;
	}

	public void setReservation(ReservationDto reservation) {
		this.reservation = reservation;
	}	

	public ReservationDetailsDTO(ReservationDto reservation,
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
