package com.example.weeklyquiz4.repository;

import com.example.weeklyquiz4.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
