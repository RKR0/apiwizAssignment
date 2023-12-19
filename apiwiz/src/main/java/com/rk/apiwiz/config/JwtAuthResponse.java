package com.rk.apiwiz.config;

import com.rk.apiwiz.dto.UserDto;

import lombok.Data;

@Data
public class JwtAuthResponse {

	private String token;
	
	private UserDto user;
}
