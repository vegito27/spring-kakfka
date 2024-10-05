package com.dashboard.kafka.dashboard_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dashboard.kafka.dashboard_backend.dto.AuthResponseDTO;
import com.dashboard.kafka.dashboard_backend.dto.LoginDTO;
import com.dashboard.kafka.dashboard_backend.service.AuthService;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
	
	 @Autowired
     private AuthService authService;

	 @PostMapping("/login")
     public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDto){

	        String token = authService.login(loginDto);

	        AuthResponseDTO authResponseDto = new AuthResponseDTO();
	        authResponseDto.setAccessToken(token);

	        return new ResponseEntity<>(authResponseDto,HttpStatus.OK);
	    }
}
