package com.example.purchase_app.service;
import java.util.Collection;
import org.springframework.stereotype.Service;

import com.example.purchase_app.dto.PurchaseDTO;
import com.example.purchase_app.model.Purchase;

@Service
public interface PurchaseService {
	public void createPurchase(Purchase purchase);
	public Collection<PurchaseDTO> getAll();
	public PurchaseDTO getByPurchaseId (Integer id);
	public PurchaseDTO getByReservationId (Integer id);
	public void delete (Integer id);
}
