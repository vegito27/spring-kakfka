package com.dashboard.kafka.dashboard_backend.authentication;

import org.springframework.security.config.Customizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	
	 private UserDetailsService userDetailsService;

	 private JwtAuthenticationEntryPoint authenticationEntryPoint;

     private JwtAuthenticationFilter authenticationFilter;

	 @Bean
	 static PasswordEncoder passwordEncoder(){
	       return new BCryptPasswordEncoder();
     }
	 
	 @Bean
	 SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http.csrf(csrf -> csrf.disable())
	                .authorizeHttpRequests((authorize) -> {
	                    authorize.requestMatchers("/api/auth/**").permitAll();
	                    authorize.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
	                    authorize.anyRequest().authenticated();
	                }).httpBasic(Customizer.withDefaults());

	        http.exceptionHandling( exception -> exception
	                .authenticationEntryPoint(authenticationEntryPoint));

	        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

	        return http.build();
	    }
	 
	 @Bean
	 AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
	        return configuration.getAuthenticationManager();
	 }
	
} 

