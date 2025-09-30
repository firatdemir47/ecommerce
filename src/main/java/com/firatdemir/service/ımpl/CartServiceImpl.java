package com.firatdemir.service.ımpl;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.firatdemir.dto.CarItemDto;
import com.firatdemir.dto.CartDto;
import com.firatdemir.model.Cart;
import com.firatdemir.model.CartItem;
import com.firatdemir.repository.CartItemRepository;
import com.firatdemir.repository.CartRepository;
import com.firatdemir.repository.ProductRepository;
import com.firatdemir.repository.UserRepository;
import com.firatdemir.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	private final CartRepository cartRepository;
	private final CartItemRepository cartItemRepository;
	private final UserRepository userRepository;
	private final ProductRepository productRepository;

	public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository,
			UserRepository userRepository, ProductRepository productRepository) {
		this.cartRepository = cartRepository;
		this.cartItemRepository = cartItemRepository;
		this.userRepository = userRepository;
		this.productRepository = productRepository;
	}

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

	private CartDto toDto(Cart cart) {
		CartDto dto = new CartDto();
		dto.setId(cart.getId());
		dto.setUserId(cart.getUser().getId());
		if (cart.getItems() != null) {
			dto.setItems(cart.getItems().stream().map(this::toDto).collect(Collectors.toList()));
		}
		return dto;
	}

	private CarItemDto toDto(CartItem item) {
		CarItemDto dto = new CarItemDto();
		dto.setId(item.getId());
		dto.setCartid(item.getCart().getId());
		dto.setProductId(item.getProduct().getId());
		dto.setQuantity(item.getQuantity());
		return dto;
	}

}
