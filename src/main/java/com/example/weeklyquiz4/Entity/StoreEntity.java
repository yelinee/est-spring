package com.example.weeklyquiz4.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreEntity {
    private int storeId;
    private String storeName;
    private Long sales;
}
