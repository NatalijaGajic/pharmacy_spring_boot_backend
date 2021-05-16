package com.example.pharmacy.jpa;

import java.sql.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@DiscriminatorValue("Client")
@Entity
public class Client extends SystemUser{
	
	private Integer numberOfPenalties;

	@ManyToMany(targetEntity = Medicine.class)
	private List<Medicine> alergy_medicines;
	
	@JsonIgnore
	@OneToMany(mappedBy = "client")
	private List<Reservation> reservations;
	
	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
	
	public Client(String username, String password, String firstName, String lastName, String email, String telephone,
			Date dateOfBirth, Integer numberOfPenalties) {
		super(username, password, firstName, lastName, email, telephone, dateOfBirth);
		this.numberOfPenalties = numberOfPenalties;
	}
	
	public Client() {
		
	}

	public Integer getNumberOfPenalties() {
		return numberOfPenalties;
	}

	public void setNumberOfPenalties(Integer numberOfPenalties) {
		this.numberOfPenalties = numberOfPenalties;
	}
	
	public List<Medicine> getAlergy_medicines() {
		return alergy_medicines;
	}

	public void setAlergy_medicines(List<Medicine> alergy_medicines) {
		this.alergy_medicines = alergy_medicines;
	}

	
}
