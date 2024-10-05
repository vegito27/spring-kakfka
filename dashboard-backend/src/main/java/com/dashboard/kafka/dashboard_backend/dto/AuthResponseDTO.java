package com.dashboard.kafka.dashboard_backend.dto;

import lombok.Data;


public class AuthResponseDTO {
	
	  public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	private String accessToken;

}
