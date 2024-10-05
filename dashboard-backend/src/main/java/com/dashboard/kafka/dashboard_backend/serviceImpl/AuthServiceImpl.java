package com.dashboard.kafka.dashboard_backend.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.dashboard.kafka.dashboard_backend.authentication.config.JwtTokenProvider;
import com.dashboard.kafka.dashboard_backend.dto.LoginDTO;
import com.dashboard.kafka.dashboard_backend.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Override
	public String login(LoginDTO loginDto) {

		Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
        ));

        /* 02 - SecurityContextHolder is used to allows the rest of the application to know
        that the user is authenticated and can use user data from Authentication object */
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 03 - Generate the token based on username and secret key
        String token = jwtTokenProvider.generateToken(authentication);

        // 04 - Return the token to controller
        return token;
	}
	
	
	
	

}
