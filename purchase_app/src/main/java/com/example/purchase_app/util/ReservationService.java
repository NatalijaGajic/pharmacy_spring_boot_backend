package com.example.purchase_app.util;
import java.util.Collection;
import org.springframework.stereotype.Service;
import com.example.purchase_app.dto.PharmacistDTO;
import com.example.purchase_app.dto.ReservationDTO;

@Service
public interface ReservationService {
	public ReservationDTO getReservationById(Integer id);
}
