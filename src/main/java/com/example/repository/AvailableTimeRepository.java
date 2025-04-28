package com.example.repository;

import com.example.entity.AvailableTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvailableTimeRepository extends JpaRepository<AvailableTime, Long> {
}
