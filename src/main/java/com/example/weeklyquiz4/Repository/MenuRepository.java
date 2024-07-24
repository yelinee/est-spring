package com.example.weeklyquiz4.repository;

import com.example.weeklyquiz4.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface MenuRepository extends JpaRepository<MenuEntity, Long> {

    // 카테고리로 메뉴 찾기
    @Query("select m from MenuEntity m where m.menuCategory = ?1")
    List<MenuEntity> findByMenuCategory(String menuCategory);
}
