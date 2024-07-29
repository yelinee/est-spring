package com.example.weeklyquiz4.dto;

import com.example.weeklyquiz4.entity.OrderEntity;
import com.example.weeklyquiz4.entity.OrderItemEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.query.Order;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDto {
    private int orderItemId;
    private int orderItemName;
    private int quantity;
    private int price;

    public static OrderItemDto fromEntity(OrderItemEntity orderItemEntity) {
        return OrderItemDto.builder()
                .orderItemId(orderItemEntity.getOrderItemId())
                .orderItemName(orderItemEntity.getOrderItemName())
                .quantity(orderItemEntity.getQuantity())
                .price(orderItemEntity.getPrice())
                .build();
    }

    public OrderItemEntity toEntity() {
        return new OrderItemEntity(
                this.orderItemId,
                this.orderItemName,
                this.quantity,
                this.price);
    }
}
