package com.example.reservation_app.utils;

import com.example.reservation_app.dto.ReservationCreationDTO;
import com.example.reservation_app.dto.ReservationDTO;
import com.example.reservation_app.model.Reservation;

public interface Mapper {

	public Reservation mapReservationCreationDtoToReservation(ReservationCreationDTO reservationCreation);
	public ReservationDTO mapReservationToReservationDto(Reservation res);
}
