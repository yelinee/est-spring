package com.example.weeklyquiz4.repository;

import com.example.weeklyquiz4.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
}
