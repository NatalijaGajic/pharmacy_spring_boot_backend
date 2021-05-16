package com.example.system_role_app.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.system_role_app.jpa.SystemRole;


public interface SystemRoleRepository extends JpaRepository<SystemRole, Integer>{

	Collection<SystemRole> findByNameContainingIgnoreCase(String name);
}
