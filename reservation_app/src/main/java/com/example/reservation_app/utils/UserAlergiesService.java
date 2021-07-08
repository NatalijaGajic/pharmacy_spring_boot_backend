package com.example.reservation_app.utils;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.example.user_alergies_app.dto.UserAlergiesDto;


@Service
public interface UserAlergiesService {

	public Collection<UserAlergiesDto> getUserAlergiesForUser(Integer id);
}
