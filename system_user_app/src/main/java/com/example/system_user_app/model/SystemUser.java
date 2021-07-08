package com.example.system_user_app.model;

import java.util.Date;

import com.example.pharmacy.dto.SystemRoleDto;

public class SystemUser {

	private Integer id;
	
	private String username;
	
	private String password;
	
	private String firstName;
	
	private String lastName;

	private String email;
	
	private String telephone;
	
	private Date dateOfBirth;
	
	private SystemRoleDto systemRole = new SystemRoleDto();


	public SystemUser(String firstName, String lastName, String password, String telephone, String username,  String email,
			 Integer systemRoleId, Date dateOfBirth) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.telephone = telephone;
		this.dateOfBirth = dateOfBirth;
		this.systemRole.setId(systemRoleId);
	}
	
	public SystemUser(String firstName, String lastName, String password, String telephone, String username,  String email,
			 Integer systemRoleId, Date dateOfBirth, Integer id) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.telephone = telephone;
		this.dateOfBirth = dateOfBirth;
		this.systemRole.setId(systemRoleId);
		this.id = id;
	}
	
	public SystemUser() {
		
	}

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

	public SystemRoleDto getSystemRole() {
		return systemRole;
	}

	public void setSystemRole(SystemRoleDto systemRole) {
		this.systemRole = systemRole;
	}
	
}
