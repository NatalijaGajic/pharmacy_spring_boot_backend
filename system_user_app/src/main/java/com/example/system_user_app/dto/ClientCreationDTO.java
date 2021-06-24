package com.example.system_user_app.dto;

import java.util.Date;

import com.example.system_user_app.model.SystemRole;

public class ClientCreationDTO {
	
	private String username;
	
	private String password;
	
	private String firstName;
	
	private String lastName;

	private String email;
	
	private String telephone;
	
	private Date dateOfBirth;
	
	private SystemRole systemRole;
	
	private Integer numberOfPenalties;
}
