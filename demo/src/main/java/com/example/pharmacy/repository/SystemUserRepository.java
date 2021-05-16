package com.example.pharmacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.*;
import com.example.pharmacy.jpa.*;

// for administrator
public interface SystemUserRepository extends JpaRepository<SystemUser, Integer>{

	Collection<SystemUser> findByUsernameContainingIgnoreCase(String username);
	Collection<SystemUser> findBySystemRole(SystemRole role);
}
