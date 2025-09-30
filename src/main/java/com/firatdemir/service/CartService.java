package com.firatdemir.service;

import com.firatdemir.dto.CartDto;

public interface CartService {

	CartDto getCartByUserId(Long userId);

	CartDto addProductToCart(Long userId, Long productId, int quantity);

	CartDto removeProductFromCart(Long userId, Long productId);

	void clearCart(Long userId);
}
