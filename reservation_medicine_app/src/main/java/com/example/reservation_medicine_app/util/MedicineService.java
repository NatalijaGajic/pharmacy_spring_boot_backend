package com.example.reservation_medicine_app.util;
import java.util.Collection;
import org.springframework.stereotype.Service;

import com.example.pharmacy.dto.MedicineDto;
import com.example.pharmacy.dto.MedicineIdsDto;


@Service
public interface MedicineService {
	public Collection<MedicineDto> getMedicinesByIds(MedicineIdsDto medicineIdsDto);
}
