package com.example.purchase_app.util;
import java.util.Collection;
import org.springframework.stereotype.Service;
import com.example.purchase_app.dto.PharmacistDTO;

@Service
public interface UserService {
	public PharmacistDTO getPharmacistsById(Integer id);
}
