package com.dashboard.kafka.dashboard_backend.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dashboard.kafka.dashboard_backend.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
