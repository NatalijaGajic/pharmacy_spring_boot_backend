package com.example.reservation_app.utils;

import java.util.Collection;

import com.example.reservation_app.dto.MedicineDto;
import com.example.reservation_app.dto.MedicineUpdateDto;
import com.example.reservation_app.dto.ReservationCreationDTO;
import com.example.reservation_app.dto.ReservationDTO;
import com.example.reservation_app.model.Reservation;

public interface Mapper {

	public Reservation mapReservationCreationDtoToReservation(ReservationCreationDTO reservationCreation);
	public ReservationDTO mapReservationToReservationDto(Reservation res);
	public MedicineUpdateDto mapMedicineDtoToMedicineUpdateDto(MedicineDto medicineDto);
	public Collection<ReservationDTO> mapReservationCollectionToReservationDTOCollection(Collection<Reservation> reservations);
}
