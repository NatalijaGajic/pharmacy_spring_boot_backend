package com.example.system_user_app.dto;

import java.util.Date;

public class PharmacistUpdateDTO {

	private String username;
	
	private String firstName;
	
	private String lastName;
	
	private String telephone;
	
	private Date dateOfBirth;
	
	private double salary;
	
	private Date startDateOfContract;

	private Date endDateOfContract;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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
