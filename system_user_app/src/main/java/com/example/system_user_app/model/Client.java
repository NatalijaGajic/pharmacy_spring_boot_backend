package com.example.system_user_app.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Client extends SystemUser{
	
	private Integer numberOfPenalties;
	
	public Client(String firstName, String lastName, String password, String telephone, String username, String email, Integer systemRoleId 
			, Date dateOfBirth, Integer id, Integer numberOfPenalties) {
		super(firstName, lastName, password, telephone, username, email, systemRoleId, dateOfBirth, id);
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
	
	
}
