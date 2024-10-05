package com.dashboard.kafka.dashboard_backend.service;

import com.dashboard.kafka.dashboard_backend.dto.LoginDTO;

public interface AuthService {
	
	 String login(LoginDTO loginDto);

}
