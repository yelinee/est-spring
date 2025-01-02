package com.example.weeklyquiz4.dto;


import com.example.weeklyquiz4.entity.MenuEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;

/**
 * DTO for {@link MenuEntity}
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuDto {
    private Long menuId;
    private String menuName;
    private String menuCategory;
    private int price;
    private String menuDescription;

    // Entity -> DTO
    public static MenuDto fromEntity(MenuEntity menuEntity) {
        return MenuDto.builder()
                .menuId(menuEntity.getMenuId())
                .menuName(menuEntity.getMenuName())
                .menuCategory(menuEntity.getMenuCategory())
                .price(menuEntity.getPrice())
                .menuDescription(menuEntity.getMenuDescription())
                .build();

    }

    public MenuEntity toEntity() {
        return new MenuEntity(
                this.menuId,
                this.menuName,
                this.menuCategory,
                this.price,
                this.menuDescription
        );
    }
}