package com.firatdemir.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.firatdemir.dto.OrderItemDto;
import com.firatdemir.service.OrderItemService;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

	private final OrderItemService orderItemService;

	public OrderItemController(OrderItemService orderItemService) {
		this.orderItemService = orderItemService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrderItemDto> getOrderItemById(@PathVariable Long id) {
		return ResponseEntity.ok(orderItemService.getOrderItemById(id));
	}

	@GetMapping("/order/{orderId}")
	public ResponseEntity<List<OrderItemDto>> getOrderItemsByOrderId(@PathVariable Long orderId) {
		return ResponseEntity.ok(orderItemService.getOrderItemsByOrderId(orderId));
	}

	@PostMapping
	public ResponseEntity<OrderItemDto> createOrderItem(@RequestBody OrderItemDto orderItemDto) {
		return ResponseEntity.ok(orderItemService.createOrderItem(orderItemDto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<OrderItemDto> updateOrderItem(@PathVariable Long id, @RequestBody OrderItemDto dto) {
		return ResponseEntity.ok(orderItemService.updateOrderItem(id, dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
		orderItemService.deleteOrderItem(id);
		return ResponseEntity.noContent().build();
	}
}
