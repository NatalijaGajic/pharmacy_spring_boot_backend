package com.example.purchase_app.dto;

import java.util.Date;


public class PharmacistDTO {

	private Integer id;
	
	private String username;
	
	private String password;
	
	private String firstName;
	
	private String lastName;

	private String email;
	
	private String telephone;
	
	private Date dateOfBirth;
	
	private SystemRoleDTO systemRole;
	
	private double salary;
	
	private Date startDateOfContract;

	private Date endDateOfContract;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public SystemRoleDTO getSystemRole() {
		return systemRole;
	}

	public void setSystemRole(SystemRoleDTO systemRole) {
		this.systemRole = systemRole;
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
