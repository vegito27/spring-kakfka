package com.dashboard.kafka.dashboard_backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class LoginDTO {
	
	 public String getUsernameOrEmail() {
		return usernameOrEmail;
	}

	public void setUsernameOrEmail(String usernameOrEmail) {
		this.usernameOrEmail = usernameOrEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@NotBlank(message = "Username or Email is mandatory")
	 private String usernameOrEmail;

	 @NotBlank(message = "Password is mandatory")
	 private String password;

}
