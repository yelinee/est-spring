package com.example.weeklyquiz4;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemEntity {
    private int orderItemId;
    private int orderItemName;
    private int quantity;
    private int price;
    // private int userId;

}
