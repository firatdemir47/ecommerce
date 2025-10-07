package com.firatdemir.service.ımpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.firatdemir.dto.CartItemDto;
import com.firatdemir.model.CartItem;
import com.firatdemir.repository.CartItemRepository;
import com.firatdemir.repository.CartRepository;
import com.firatdemir.repository.ProductRepository;
import com.firatdemir.service.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService {

	private final CartItemRepository cartItemRepository;
	private final CartRepository cartRepository;
	private final ProductRepository productRepository;

	public CartItemServiceImpl(CartItemRepository cartItemRepository, CartRepository cartRepository,
			ProductRepository productRepository) {
		this.cartItemRepository = cartItemRepository;
		this.cartRepository = cartRepository;
		this.productRepository = productRepository;
	}

	@Override
	public CartItemDto getCartItemById(Long id) {
		CartItem item = cartItemRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("CartItem bulunamadı, id: " + id));
		return toDto(item);
	}

	@Override
	public List<CartItemDto> getAllCartItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CartItemDto createCartItem(CartItemDto cartItemDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CartItemDto updateCartItem(Long id, CartItemDto cartItemDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCartItem(Long id) {
		// TODO Auto-generated method stub

	}

	private CartItemDto toDto(CartItem item) {
		CartItemDto dto = new CartItemDto();
		dto.setId(item.getId());
		dto.setCartid(item.getCart().getId());
		dto.setProductId(item.getProduct().getId());
		dto.setQuantity(item.getQuantity());
		return dto;
	}

}
