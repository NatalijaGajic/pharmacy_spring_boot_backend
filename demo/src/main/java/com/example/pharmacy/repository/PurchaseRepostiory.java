package com.example.pharmacy.repository;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pharmacy.jpa.Purchase;

public interface PurchaseRepostiory extends JpaRepository<Purchase, Integer>{

	Collection<Purchase> findAllByDateOfPurchase(Date dateOfPurchase);
	Collection<Purchase> findAllByDateOfPurchaseBetween(Date dateOfPurchaseStart, Date dateOfPurchaseEnd);
}
