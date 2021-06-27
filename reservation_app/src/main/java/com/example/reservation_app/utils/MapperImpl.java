package com.example.reservation_app.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.reservation_app.dto.ClientDTO;
import com.example.reservation_app.dto.MedicineDto;
import com.example.reservation_app.dto.MedicineUpdateDto;
import com.example.reservation_app.dto.ReservationCreationDTO;
import com.example.reservation_app.dto.ReservationDTO;
import com.example.reservation_app.dto.ReservationUpdateDto;
import com.example.reservation_app.model.Reservation;

@Component
public class MapperImpl implements Mapper {

	@Autowired
	private ClientService clientService; 
	
	public Reservation mapReservationCreationDtoToReservation(ReservationCreationDTO reservationCreation) {
		UUID code = UUID.randomUUID();
		return new Reservation(null, reservationCreation.getDateOfReservation(), reservationCreation.getDateOfPickUp(), reservationCreation.isCancelled(), reservationCreation.getStatus(), reservationCreation.getClientId(), code);
	}
	
	public ReservationDTO mapReservationToReservationDto(Reservation res) {
		ReservationDTO dto = new ReservationDTO(res.getId(), res.getReservationCode(), res.getDateOfReservation(), res.getDateOfPickUp(), res.getPrice(), res.isCancelled(), res.getStatus());
		ClientDTO client =  clientService.getClientById(res.getClientId());
		dto.setClient(client);
		return dto;
	}

	@Override
	public MedicineUpdateDto mapMedicineDtoToMedicineUpdateDto(MedicineDto medicineDto) {
		MedicineUpdateDto updateDto = new MedicineUpdateDto(medicineDto.getName(), medicineDto.getPrice(), medicineDto.getManufacturer(), medicineDto.getDescription(), medicineDto.getQuantity());
		return updateDto;
	}

	@Override
	public Collection<ReservationDTO> mapReservationCollectionToReservationDTOCollection(
			Collection<Reservation> reservations) {
		Collection<ReservationDTO> dtos = new ArrayList<ReservationDTO>();
		for(Reservation res: reservations) {
			dtos.add(mapReservationToReservationDto(res));
		}
		return dtos;
	}
}
