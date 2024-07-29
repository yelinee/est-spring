package com.example.weeklyquiz4.dto;

import com.example.weeklyquiz4.entity.OrderEntity;
import com.example.weeklyquiz4.entity.OrderItemEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.criteria.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long orderId;
    private String orderStatus;
    private int totalCost;
    private List<OrderItemEntity> orderItems;

    public static OrderDto fromEntity(OrderEntity orderEntity) {
        return OrderDto.builder()
                .orderId(orderEntity.getOrderId())
                .orderStatus(orderEntity.getOrderStatus())
                .totalCost(orderEntity.getTotalCost())
                .orderItems(orderEntity.getOrderItems())
                .build();
    }

    public OrderEntity toEntity() {
        return new OrderEntity(
                this.orderId,
                this.orderStatus,
                this.totalCost,
                this.orderItems
        );
    }
}
