package com.firatdemir.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.firatdemir.dto.CartItemDto;
import com.firatdemir.service.CartItemService;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {

	private final CartItemService cartItemService;

	public CartItemController(CartItemService cartItemService) {
		this.cartItemService = cartItemService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<CartItemDto> getCartItemById(@PathVariable Long id) {
		return ResponseEntity.ok(cartItemService.getCartItemById(id));
	}

	@GetMapping
	public ResponseEntity<List<CartItemDto>> getAllCartItems() {
		return ResponseEntity.ok(cartItemService.getAllCartItems());
	}
	
	@PostMapping
    public ResponseEntity<CartItemDto> createCartItem(@RequestBody CartItemDto dto) {
        return ResponseEntity.ok(cartItemService.createCartItem(dto));
    }
}
