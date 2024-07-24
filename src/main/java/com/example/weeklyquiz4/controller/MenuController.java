package com.example.weeklyquiz4.controller;

import com.example.weeklyquiz4.dto.MenuDto;
import com.example.weeklyquiz4.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    // 메뉴 추가
    @PostMapping
    public ResponseEntity<MenuDto> addMenu(@RequestBody MenuDto menuDto) {
        MenuDto addedMenu = menuService.addMenu(menuDto);
        return new ResponseEntity<>(addedMenu, HttpStatus.CREATED);
    }

    // 전체 메뉴 조회
    @GetMapping
    public ResponseEntity<List<MenuDto>> getAllMenus() {
        List<MenuDto> menus = menuService.getAllMenus();
        return ResponseEntity.ok(menus);
    }

    // 특정 메뉴 조회
    @GetMapping("/{id}")
    public ResponseEntity<MenuDto> getMenuById(@PathVariable("id") Long id) {
        return menuService.getMenuById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 메뉴 정보 수정
    @PutMapping("/{id}")
    public ResponseEntity<MenuDto> updateMenu(@PathVariable("id") Long id, @RequestBody MenuDto menuDto) {
        return menuService.updateMenu(id, menuDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 메뉴 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable("id") Long id) {
        boolean deleted = menuService.deleteMenu(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // 특정 카테고리에 해당하는 메뉴 검색
    @GetMapping("/search/category")
    public ResponseEntity<List<MenuDto>> searchMenuByCategory(@RequestParam String category) {
        List<MenuDto> menus = menuService.searchMenuByCategory(category);
        return ResponseEntity.ok(menus);
    }

}
