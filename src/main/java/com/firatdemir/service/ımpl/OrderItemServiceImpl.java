package com.firatdemir.service.ımpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.firatdemir.dto.OrderItemDto;
import com.firatdemir.model.OrderItem;
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

		OrderItem item = orderItemRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Order item not found, id: " + id));
		return toDto(item);
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

	private OrderItemDto toDto(OrderItem entity) {
		OrderItemDto dto = new OrderItemDto();
		dto.setId(entity.getId());
		dto.setOrderId(entity.getOrder().getId());
		dto.setProductID(entity.getProduct().getId());
		dto.setQuantity(entity.getQuantity());
		dto.setPrice(entity.getPrice());
		return dto;
	}

}
