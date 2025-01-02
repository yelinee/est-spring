package com.example.weeklyquiz4.service;

import com.example.weeklyquiz4.dto.OrderDto;
import com.example.weeklyquiz4.dto.StoreDto;
import com.example.weeklyquiz4.entity.OrderEntity;
import com.example.weeklyquiz4.repository.OrderItemRepository;
import com.example.weeklyquiz4.repository.OrderRepository;
import com.example.weeklyquiz4.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Transactional
    public OrderDto createOrder(OrderDto orderDto) {
        OrderEntity orderEntity = orderDto.toEntity();
        OrderEntity orderEntitySaved = orderRepository.save(orderEntity);
        return OrderDto.fromEntity(orderEntitySaved);
    }

    @Transactional(readOnly= true)
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(OrderDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<OrderDto> getOrderById(Integer id) {
        return orderRepository.findById(id)
                .map(OrderDto::fromEntity);
    }

    @Transactional
    public Optional<OrderDto> updateOrder(Integer id, OrderDto orderDto) {
        return orderRepository.findById(id)
                .map(existingOrder -> {
                    existingOrder.setOrderStatus(orderDto.getOrderStatus());
                    existingOrder.setTotalCost(orderDto.getTotalCost());
                    existingOrder.setOrderItems(orderDto.getOrderItems());
                    return OrderDto.fromEntity(orderRepository.save(existingOrder));
                });
    }

    @Transactional
    public boolean deleteOrder(Integer id) {
        return orderRepository.findById(id)
                .map(order -> {
                    orderRepository.delete(order);
                    return true;
                })
                .orElse(false);
    }


}
