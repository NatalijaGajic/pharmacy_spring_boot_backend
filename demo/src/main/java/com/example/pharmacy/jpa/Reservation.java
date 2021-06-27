package com.example.pharmacy.jpa;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = false, unique = true)
	private UUID reservationCode;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dateOfReservation;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dateOfPickUp;

	@Column(nullable = false)
	private double price;

	private boolean isCancelled;
	
	private String status;
	
	@ManyToOne
	private Client client;
	
	@OneToMany(mappedBy = "reservation")
	private List<ReservationMedicine> reservationMedicines;

	public Reservation(Date dateOfReservation, Date dateOfPickUp, boolean isCancelled, String status, Client client) {
		super();
		this.dateOfReservation = dateOfReservation;
		this.dateOfPickUp = dateOfPickUp;
		this.isCancelled = isCancelled;
		this.status = status;
		this.client = client;
	}
	
	public Reservation() {
		
	}
	

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<ReservationMedicine> getReservationMedicines() {
		return reservationMedicines;
	}

	public void setReservationMedicines(List<ReservationMedicine> reservationMedicines) {
		this.reservationMedicines = reservationMedicines;
	}

	
	

}
