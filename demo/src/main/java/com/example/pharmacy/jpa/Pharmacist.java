package com.example.pharmacy.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@DiscriminatorValue("Pharmacist")
@Entity
public class Pharmacist extends SystemUser{
	
	private double salary;
	
	@Temporal(TemporalType.DATE)
	private Date startDateOfContract;
	
	@Temporal(TemporalType.DATE)
	private Date endDateOfContract;
	
	@OneToMany(mappedBy = "pharmacist")
	private List<Purchase> purchases;

	public Pharmacist(String username, String password, String firstName, String lastName, String email,
			String telephone, Date dateOfBirth, double salary, Date startDateOfContract, Date endDateOfContract) {
		super(username, password, firstName, lastName, email, telephone, dateOfBirth);
		this.salary = salary;
		this.startDateOfContract = startDateOfContract;
		this.endDateOfContract = endDateOfContract;
	}
	
	public Pharmacist() {
		
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Date getStartDateOfContract() {
		return startDateOfContract;
	}

	public void setStartDateOfContract(Date startDateOfContract) {
		this.startDateOfContract = startDateOfContract;
	}

	public Date getEndDateOfContract() {
		return endDateOfContract;
	}

	public void setEndDateOfContract(Date endDateOfContract) {
		this.endDateOfContract = endDateOfContract;
	}
	
	
	
}
