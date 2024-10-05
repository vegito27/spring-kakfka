package com.dashboard.kafka.dashboard_backend.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dashboard.kafka.dashboard_backend.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
    Optional<User> findByUsernameOrEmail(String username, String email);

}
