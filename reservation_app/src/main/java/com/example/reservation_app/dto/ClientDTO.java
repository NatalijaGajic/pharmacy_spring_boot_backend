package com.example.reservation_app.dto;
import java.util.Date;



public class ClientDTO {

	private Integer id;
	
	private String username;
	
	private String password;
	
	private String firstName;
	
	private String lastName;

	private String email;
	
	private String telephone;
	
	private Date dateOfBirth;
	
	private SystemRoleDTO systemRole;
	
	private Integer numberOfPenalties;

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

	public Integer getNumberOfPenalties() {
		return numberOfPenalties;
	}

	public void setNumberOfPenalties(Integer numberOfPenalties) {
		this.numberOfPenalties = numberOfPenalties;
	}
}
