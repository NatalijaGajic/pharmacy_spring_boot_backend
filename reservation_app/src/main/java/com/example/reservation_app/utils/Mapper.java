package com.example.reservation_app.utils;

import java.util.Collection;

import com.example.pharmacy.dto.MedicineDto;
import com.example.pharmacy.dto.MedicineUpdateDto;
import com.example.reservation_app.dto.ReservationCreationDto;
import com.example.reservation_app.dto.ReservationDto;
import com.example.reservation_app.model.Reservation;

public interface Mapper {

	public Reservation mapReservationCreationDtoToReservation(ReservationCreationDto reservationCreation);
	public ReservationDto mapReservationToReservationDto(Reservation res);
	public MedicineUpdateDto mapMedicineDtoToMedicineUpdateDto(MedicineDto medicineDto);
	public Collection<ReservationDto> mapReservationCollectionToReservationDTOCollection(Collection<Reservation> reservations);
}
