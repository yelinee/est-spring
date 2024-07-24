package com.example.weeklyquiz4.service;

import com.example.weeklyquiz4.dto.MenuDto;
import com.example.weeklyquiz4.entity.MenuEntity;
import com.example.weeklyquiz4.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Transactional
    public MenuDto addMenu(MenuDto menuDto) {
        MenuEntity menuEntity = menuDto.toEntity();
        MenuEntity savedMenu = menuRepository.save(menuEntity);
        return MenuDto.fromEntity(savedMenu);
    }

    @Transactional(readOnly = true)
    public List<MenuDto> getAllMenus() {
        return menuRepository.findAll().stream()
                .map(MenuDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<MenuDto> getMenuById(Long id) {
        return menuRepository.findById(id)
                .map(MenuDto::fromEntity);
    }

    @Transactional
    public Optional<MenuDto> updateMenu(Long id, MenuDto menuDto) {
        return menuRepository.findById(id)
                .map(existingMenu -> {
                    existingMenu.setMenuName(menuDto.getMenuName());
                    existingMenu.setMenuCategory(menuDto.getMenuCategory());
                    existingMenu.setPrice(menuDto.getPrice());
                    existingMenu.setMenuDescription(menuDto.getMenuDescription());
                    return MenuDto.fromEntity(menuRepository.save(existingMenu));
                });
    }

    @Transactional
    public boolean deleteMenu(Long id) {
        return menuRepository.findById(id)
                .map(menu -> {
                    menuRepository.delete(menu);
                    return true;
                })
                .orElse(false);
    }

    @Transactional(readOnly = true)
    public List<MenuDto> searchMenuByCategory(String category) {
        return menuRepository.findByMenuCategory(category).stream()
                .map(MenuDto::fromEntity)
                .collect(Collectors.toList());
    }
}
