package com.example.pharmacy.utils;

import com.example.pharmacy.dto.ReservationCreationDto;
import com.example.pharmacy.jpa.Reservation;

public interface IMapper {

	public Reservation convertReservationCreationDtoToReservation(ReservationCreationDto reservationCreation);
}
