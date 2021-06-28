package com.example.pharmacy.dto;


public class SystemRoleDto {

	private Integer id;
	
	private String name;
	
	private String description;
	
	public SystemRoleDto() {
		
	}
	
	public SystemRoleDto(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
