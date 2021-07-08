package com.example.purchase_app.util;
import java.util.Collection;
import org.springframework.stereotype.Service;

import com.example.pharmacy.dto.PharmacistDto;

@Service
public interface UserService {
	public PharmacistDto getPharmacistsById(Integer id);
}
