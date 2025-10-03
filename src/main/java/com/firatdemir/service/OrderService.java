package com.firatdemir.service;

import java.util.List;

import com.firatdemir.dto.OrderDto;

public interface OrderService {

	OrderDto createOrder(Long userId);

	OrderDto getOrderById(Long orderId);

	List<OrderDto> getOrdersByUserId(Long UserId);

	List<OrderDto> getAllOrders();
}
