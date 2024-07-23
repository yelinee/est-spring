package com.example.weeklyquiz4.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuEntity {
    private String menuName;
    private String menuCategory;
    private String price;
    private String menuDescription;

}
