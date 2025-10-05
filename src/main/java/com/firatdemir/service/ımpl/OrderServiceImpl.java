package com.firatdemir.service.ımpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.firatdemir.dto.OrderDto;
import com.firatdemir.dto.OrderItemDto;
import com.firatdemir.model.Cart;
import com.firatdemir.model.CartItem;
import com.firatdemir.model.Order;
import com.firatdemir.model.OrderItem;
import com.firatdemir.model.User;
import com.firatdemir.repository.CartItemRepository;
import com.firatdemir.repository.CartRepository;
import com.firatdemir.repository.OrderItemRepository;
import com.firatdemir.repository.OrderRepository;
import com.firatdemir.repository.UserRepository;
import com.firatdemir.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	private final OrderItemRepository orderItemRepository;
	private final UserRepository userRepository;
	private final CartRepository cartRepository;
	private final CartItemRepository cartItemRepository;

	public OrderServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository,
			UserRepository userRepository, CartRepository cartRepository, CartItemRepository cartItemRepository) {

		this.orderRepository = orderRepository;
		this.orderItemRepository = orderItemRepository;
		this.userRepository = userRepository;
		this.cartRepository = cartRepository;
		this.cartItemRepository = cartItemRepository;
	}

	@Override
	public OrderDto createOrder(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı ,id: " + userId));

		Cart cart = cartRepository.findAll().stream().filter(c -> c.getUser().getId().equals(userId)).findFirst()
				.orElseThrow(() -> new RuntimeException("Kullanıcının sepeti boş "));
		if (cart.getItems() == null || cart.getItems().isEmpty()) {
			throw new RuntimeException("Sepette ürün yok");
		}

		Order order = new Order();
		order.setUser(user);
		order.setOrderDate(LocalDateTime.now());
		order = orderRepository.save(order);

		for (CartItem cartItem : cart.getItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setOrder(order);
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setPrice(cartItem.getProduct().getPrice());
			orderItemRepository.save(orderItem);
		}
		order = orderRepository.save(order);
		Long orderId = order.getId();

		Order orderWithItems = orderRepository.findById(orderId)
				.orElseThrow(() -> new RuntimeException("Sipariş bulunamadı, id: " + orderId));

		List<OrderItem> items = orderItemRepository.findAll().stream().filter(i -> i.getOrder().getId().equals(orderId))
				.collect(Collectors.toList());
		orderWithItems.setItems(items);

		OrderDto orderDto = toDto(orderWithItems);

		cartItemRepository.deleteAll(cart.getItems());
		cart.getItems().clear();
		cartRepository.save(cart);

		return orderDto;

	}

	@Override
	public OrderDto getOrderById(Long orderId) {
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new RuntimeException("Sipariş bulunamadı, id: " + orderId));
		return toDto(order);

	}

	@Override
	public List<OrderDto> getOrdersByUserId(Long UserId) {
		return orderRepository.findAll().stream().filter(o -> o.getUser().getId().equals(UserId)).map(this::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<OrderDto> getAllOrders() {
		return orderRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());

	}

	private OrderDto toDto(Order order) {
		OrderDto dto = new OrderDto();
		dto.setId(order.getId());
		dto.setOrderDate(order.getOrderDate());
		dto.setUserId(order.getUser().getId());
		if (order.getItems() != null) {
			dto.setItems(order.getItems().stream().map(this::toDto).collect(Collectors.toList()));
		}
		return dto;
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
