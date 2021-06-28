package com.example.pharmacy.utils;

import org.springframework.stereotype.Component;

import com.example.pharmacy.dto.ReservationCreationDto;
import com.example.pharmacy.jpa.Reservation;

@Component
public class MapperImpl implements Mapper {

	public Reservation convertReservationCreationDtoToReservation(ReservationCreationDto reservationCreation) {
		return new Reservation(reservationCreation.getDateOfReservation(), reservationCreation.getDateOfPickUp(), reservationCreation.isCancelled(), reservationCreation.getStatus(), null);
	}
	
}
