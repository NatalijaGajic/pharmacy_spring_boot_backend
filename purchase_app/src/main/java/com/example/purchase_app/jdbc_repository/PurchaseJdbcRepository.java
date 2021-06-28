package com.example.purchase_app.jdbc_repository;

import java.util.Collection;

import com.example.purchase_app.model.Purchase;

public interface PurchaseJdbcRepository {
	public void save(Purchase purchase);
	public Collection<Purchase> findAll();
	public Purchase findByPurchaseId (Integer id);
	public Purchase findByReservationId (Integer id);
	public void delete (Integer id);
}
