package com.example.weeklyquiz4.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {
   //여러 메뉴

    @Id
    @Column(name="order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name="order_status")
    private String orderStatus;

    @Column(name="total_cost")
    private int totalCost;

    @Column(name="order_items")
    private List<OrderItemEntity> orderItems = new ArrayList<>();




}
