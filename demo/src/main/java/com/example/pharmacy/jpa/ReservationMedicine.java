package com.example.pharmacy.jpa;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ReservationMedicine {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Integer amount;
	
	@ManyToOne
	@JoinColumn(name = "medicine_id")
	private Medicine medicine;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "reservation_id")
	private Reservation reservation;

	public ReservationMedicine() {
		
	}
	
	public ReservationMedicine(Integer id, Integer amount, Medicine medicine, Reservation reservation) {
		super();
		this.id = id;
		this.amount = amount;
		this.medicine = medicine;
		this.reservation = reservation;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	
	
}
