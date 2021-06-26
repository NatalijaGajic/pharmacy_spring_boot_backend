package com.example.reservation_app.utils;

import org.springframework.stereotype.Service;

import com.example.reservation_app.dto.UserAlergiesDTO;

@Service
public interface UserAlergiesService {

	public UserAlergiesDTO getUserAlergiesForUser(Integer id);
}
