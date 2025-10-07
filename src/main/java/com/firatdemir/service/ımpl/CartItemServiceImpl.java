package com.firatdemir.service.ımpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.firatdemir.dto.CartItemDto;
import com.firatdemir.model.Cart;
import com.firatdemir.model.CartItem;
import com.firatdemir.model.Product;
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
		return cartItemRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
	}

	@Override
	public CartItemDto createCartItem(CartItemDto cartItemDto) {
		Cart cart = cartRepository.findById(cartItemDto.getCartid())
				.orElseThrow(() -> new RuntimeException("Cart bulunamadı, id: " + cartItemDto.getCartid()));
		Product product = productRepository.findById(cartItemDto.getProductId())
				.orElseThrow(() -> new RuntimeException("Ürün bulunamadı, id: " + cartItemDto.getProductId()));

		CartItem item = new CartItem();
		item.setCart(cart);
		item.setProduct(product);
		item.setQuantity(cartItemDto.getQuantity());

		CartItem saved = cartItemRepository.save(item);
		return toDto(saved);
	}

	@Override
	public CartItemDto updateCartItem(Long id, CartItemDto cartItemDto) {
		 CartItem item = cartItemRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("CartItem bulunamadı, id: " + id));

	        item.setQuantity(cartItemDto.getQuantity());
	        CartItem updated = cartItemRepository.save(item);

	        return toDto(updated);
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
