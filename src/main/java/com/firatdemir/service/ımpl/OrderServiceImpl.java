package com.firatdemir.service.ımpl;

import java.util.List;

import com.firatdemir.dto.OrderDto;
import com.firatdemir.dto.OrderItemDto;
import com.firatdemir.model.OrderItem;
import com.firatdemir.service.OrderService;

public class OrderServiceImpl implements OrderService {

	@Override
	public OrderDto createOrder(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDto getOrderById(Long orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderDto> getOrdersByUserId(Long UserId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderDto> getAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	private OrderItemDto toDto(OrderItem item) {
		OrderItemDto dto = new OrderItemDto();
		dto.setId(item.getId());
		dto.setOrderId(item.getOrder().getId());
		dto.setProductID(item.getProduct().getId());
		dto.setQuantity(item.getQuantity());
		dto.setPrice(item.getPrice());
		return dto;
	}

}
