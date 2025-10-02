package com.firatdemir.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.firatdemir.dto.CartDto;
import com.firatdemir.service.CartService;

@RestController
@RequestMapping("/api/carts")
public class CartController {

	private final CartService cartService;

	public CartController(CartService cartService) {
		this.cartService = cartService;
	}

	@GetMapping("/{userId}")
	public ResponseEntity<CartDto> getCartByUserId(@PathVariable Long userId) {
		return ResponseEntity.ok(cartService.getCartByUserId(userId));
	}

	@PostMapping("/{userId}/add/{productId}")
	public ResponseEntity<CartDto> addProductToCart(@PathVariable Long userId, @PathVariable Long productId,
			@RequestParam(defaultValue = "1") int quantity) {
		return ResponseEntity.ok(cartService.addProductToCart(userId, productId, quantity));
	}

	@DeleteMapping("/{userId}/remove/{productId}")
	public ResponseEntity<CartDto> removeProductFromCart(@PathVariable Long userId, @PathVariable Long productId) {
		return ResponseEntity.ok(cartService.removeProductFromCart(userId, productId));
	}

	@DeleteMapping("/{userId}/clear")
	public ResponseEntity<Void> clearCart(@PathVariable Long userId) {
		cartService.clearCart(userId);
		return ResponseEntity.noContent().build();
	}
}
