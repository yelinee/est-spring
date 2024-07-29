package com.example.weeklyquiz4.repository;

import com.example.weeklyquiz4.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Integer> {

}
