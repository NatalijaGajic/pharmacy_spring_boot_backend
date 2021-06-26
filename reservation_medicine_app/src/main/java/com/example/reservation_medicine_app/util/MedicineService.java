package com.example.reservation_medicine_app.util;
import java.util.Collection;
import org.springframework.stereotype.Service;
import com.example.reservation_medicine_app.dto.MedicineDto;

@Service
public interface MedicineService {
	public Collection<MedicineDto> getMedicinesByIds(Collection<Integer> ids);
}