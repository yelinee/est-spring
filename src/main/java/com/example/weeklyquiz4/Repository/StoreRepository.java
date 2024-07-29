package com.example.weeklyquiz4.repository;

import com.example.weeklyquiz4.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {
}
