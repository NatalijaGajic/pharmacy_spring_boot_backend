package com.example.reservation_medicine_app.service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.reservation_medicine_app.dto.MedicineDto;
import com.example.reservation_medicine_app.dto.ReservationMedicineDto;
import com.example.reservation_medicine_app.jdbc_repository.ReservationMedicineJdbcRepository;
import com.example.reservation_medicine_app.model.ReservationMedicine;
import com.example.reservation_medicine_app.util.MedicineService;

@Service
public class ReservationMedicineServiceImpl implements ReservationMedicineService{

	@Autowired
	private ReservationMedicineJdbcRepository reservationMedicineRepository;

	@Autowired
	private MedicineService medicineService;
 
	@Override
	public Collection<ReservationMedicineDto> getMedicinesByReservationId(Integer id) throws Exception{
		List<ReservationMedicine> reservationMedicines = (List<ReservationMedicine>) reservationMedicineRepository.findByReservationId(id);
		Collection<Integer> ids = new ArrayList<Integer>();
		for (ReservationMedicine rm:reservationMedicines) {
			  ids.add(rm.getMedicineId());
			}
		Collection<MedicineDto> medicines = medicineService.getMedicinesByIds(ids);
		Collection<ReservationMedicineDto> reservationMedicineDtos = new ArrayList<ReservationMedicineDto>();
		Integer index = 0;
		for (MedicineDto med: medicines) {
			Integer amount = reservationMedicines.get(index).getAmount();
			ReservationMedicineDto reservationMedicineDto = new ReservationMedicineDto(med, amount);
			reservationMedicineDtos.add(reservationMedicineDto);
			index ++;
		}
		return reservationMedicineDtos;
	}

	@Override
	public void createReservationMedicine(Collection<ReservationMedicine> reservationMedicine) throws Exception {
		for(ReservationMedicine rm: reservationMedicine) {
			reservationMedicineRepository.save(rm);
		}
	}

}
