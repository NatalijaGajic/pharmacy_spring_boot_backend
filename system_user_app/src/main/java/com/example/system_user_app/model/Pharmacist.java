package com.example.system_user_app.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

public class Pharmacist extends SystemUser{
	
	private double salary;
	
	private Date startDateOfContract;
	private Date endDateOfContract;

	public Pharmacist(String firstName, String lastName, String password, String telephone, String username, String email, Integer systemRoleId 
			, Date dateOfBirth, Integer id, double salary, Date startDateOfContract, Date endDateOfContract) {
		super(firstName, lastName, password, telephone, username, email, systemRoleId, dateOfBirth, id);
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
