package com.example.pharmacy.dto;

public class SystemRoleCreationDto {
	private String name;
	
	private String description;
	
	public SystemRoleCreationDto() {
		
	}
	
	public SystemRoleCreationDto(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
