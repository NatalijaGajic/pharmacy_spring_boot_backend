package com.example.purchase_app.util;
import java.util.Collection;
import org.springframework.stereotype.Service;

import com.example.pharmacy.dto.ReservationDto;

@Service
public interface ReservationService {
	public ReservationDto getReservationById(Integer id);
}
