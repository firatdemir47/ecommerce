package com.firatdemir.service.ımpl;

import org.springframework.stereotype.Service;

import com.firatdemir.dto.CartDto;
import com.firatdemir.service.CartService;

@Service
public class CartServiceImpl  implements CartService{

	@Override
	public CartDto getCartByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CartDto addProductToCart(Long userId, Long productId, int quantity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CartDto removeProductFromCart(Long userId, Long productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearCart(Long userId) {
		// TODO Auto-generated method stub
		
	}
	
	
}
