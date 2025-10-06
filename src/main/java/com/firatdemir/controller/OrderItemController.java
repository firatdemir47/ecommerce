package com.firatdemir.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.firatdemir.service.OrderItemService;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

	private final OrderItemService orderItemService;

	public OrderItemController(OrderItemService orderItemService) {
		this.orderItemService = orderItemService;
	}
}
