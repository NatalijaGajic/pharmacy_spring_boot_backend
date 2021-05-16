package com.example.pharmacy.dto;

public class ReservationUpdateDto {
	private String status;

	public ReservationUpdateDto() {
	}
	
	public ReservationUpdateDto(String status) {
		super();
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
