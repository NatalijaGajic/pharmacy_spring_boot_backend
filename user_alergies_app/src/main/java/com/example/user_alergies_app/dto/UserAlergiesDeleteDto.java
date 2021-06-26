package com.example.user_alergies_app.dto;

import java.util.Collection;

public class UserAlergiesDeleteDto {

	private Collection<Integer> medicinesId;

	public Collection<Integer> getMedicinesId() {
		return medicinesId;
	}

	public void setMedicinesId(Collection<Integer> medicinesId) {
		this.medicinesId = medicinesId;
	}

	public UserAlergiesDeleteDto() {
		super();
	}
	
}
