package com.firatdemir.service;

import java.util.List;

import com.firatdemir.dto.OrderItemDto;

public interface OrderItemService {

	OrderItemDto getOrderItemById(Long id);

	List<OrderItemDto> getOrderItemsByOrderId(Long orderId);

	OrderItemDto createOrderItem(OrderItemDto orderItemDto);

	OrderItemDto updateOrderItem(Long id, OrderItemDto orderItemDto);

	void deleteOrderItem(Long id);
}
