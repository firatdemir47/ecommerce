package com.firatdemir.service.ımpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.firatdemir.dto.OrderItemDto;
import com.firatdemir.repository.OrderItemRepository;
import com.firatdemir.repository.OrderRepository;
import com.firatdemir.repository.ProductRepository;
import com.firatdemir.service.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService {

	private final OrderItemRepository orderItemRepository;
	private final OrderRepository orderRepository;
	private final ProductRepository productRepository;

	public OrderItemServiceImpl(OrderItemRepository orderItemRepository, OrderRepository orderRepository,
			ProductRepository productRepository) {
		this.orderItemRepository = orderItemRepository;
		this.orderRepository = orderRepository;
		this.productRepository = productRepository;
	}

	@Override
	public OrderItemDto getOrderItemById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderItemDto> getOrderItemsByOrderId(Long orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderItemDto createOrderItem(OrderItemDto orderItemDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderItemDto updateOrderItem(Long id, OrderItemDto orderItemDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteOrderItem(Long id) {
		// TODO Auto-generated method stub

	}

}
