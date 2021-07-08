package com.example.reservation_app.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.pharmacy.dto.ClientDto;
import com.example.pharmacy.dto.MedicineDto;
import com.example.pharmacy.dto.MedicineUpdateDto;
import com.example.reservation_app.dto.ReservationCreationDto;
import com.example.reservation_app.dto.ReservationDto;
import com.example.reservation_app.model.Reservation;

@Component
public class MapperImpl implements Mapper {

	@Autowired
	private ClientService clientService; 
	
	public Reservation mapReservationCreationDtoToReservation(ReservationCreationDto reservationCreation) {
		UUID code = UUID.randomUUID();
		return new Reservation(null, reservationCreation.getDateOfReservation(), reservationCreation.getDateOfPickUp(), reservationCreation.isCancelled(), reservationCreation.getStatus(), reservationCreation.getClientId(), code);
	}
	
	public ReservationDto mapReservationToReservationDto(Reservation res) {
		ReservationDto dto = new ReservationDto(res.getId(), res.getReservationCode(), res.getDateOfReservation(), res.getDateOfPickUp(), res.getPrice(), res.isCancelled(), res.getStatus());
		ClientDto client =  clientService.getClientById(res.getClientId());
		dto.setClient(client);
		return dto;
	}

	@Override
	public MedicineUpdateDto mapMedicineDtoToMedicineUpdateDto(MedicineDto medicineDto) {
		MedicineUpdateDto updateDto = new MedicineUpdateDto(medicineDto.getName(), medicineDto.getPrice(), medicineDto.getManufacturer(), medicineDto.getDescription(), medicineDto.getQuantity());
		return updateDto;
	}

	@Override
	public Collection<ReservationDto> mapReservationCollectionToReservationDTOCollection(
			Collection<Reservation> reservations) {
		Collection<ReservationDto> dtos = new ArrayList<ReservationDto>();
		for(Reservation res: reservations) {
			dtos.add(mapReservationToReservationDto(res));
		}
		return dtos;
	}
}
