package com.firatdemir.service;

import java.util.List;

import com.firatdemir.dto.CartItemDto;

public interface CartItemService {
	CartItemDto getCartItemById(Long id);

	List<CartItemDto> getAllCartItems();

	CartItemDto createCartItem(CartItemDto cartItemDto);

	CartItemDto updateCartItem(Long id, CartItemDto cartItemDto);

	void deleteCartItem(Long id);

}
