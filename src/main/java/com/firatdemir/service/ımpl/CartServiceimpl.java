package com.firatdemir.service.ımpl;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.firatdemir.dto.CartDto;
import com.firatdemir.dto.CartItemDto;
import com.firatdemir.model.Cart;
import com.firatdemir.model.CartItem;
import com.firatdemir.model.Product;
import com.firatdemir.model.User;
import com.firatdemir.repository.CartItemRepository;
import com.firatdemir.repository.CartRepository;
import com.firatdemir.repository.ProductRepository;
import com.firatdemir.repository.UserRepository;
import com.firatdemir.service.CartService;

@Service
public class CartServiceimpl implements CartService {

	private final CartRepository cartRepository;
	private final CartItemRepository cartItemRepository;
	private final UserRepository userRepository;
	private final ProductRepository productRepository;

	public CartServiceimpl(CartRepository cartRepository, CartItemRepository cartItemRepository,
			UserRepository userRepository, ProductRepository productRepository) {
		this.cartRepository = cartRepository;
		this.cartItemRepository = cartItemRepository;
		this.userRepository = userRepository;
		this.productRepository = productRepository;
	}

	@Override
	public CartDto getCartByUserId(Long userId) {
		Cart cart = cartRepository.findAll().stream().filter(c -> c.getUser().getId().equals(userId)).findFirst()
				.orElseGet(() -> {
					User user = userRepository.findById(userId)
							.orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı, id: " + userId));
					Cart newCart = new Cart();
					newCart.setUser(user);
					return cartRepository.save(newCart);
				});
		return toDto(cart);

	}

	@Override
	@Transactional
	public CartDto addProductToCart(Long userId, Long productId, int quantity) {
		Cart cart = getOrCreateCart(userId);
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Ürün bulunamadı, id: " + productId));

		CartItem existingItem = cart.getItems().stream().filter(i -> i.getProduct().getId().equals(productId))
				.findFirst().orElse(null);

		if (existingItem != null) {
			existingItem.setQuantity(existingItem.getQuantity() + quantity);
			cartItemRepository.save(existingItem);
		} else {
			CartItem newItem = new CartItem();
			newItem.setCart(cart);
			newItem.setProduct(product);
			newItem.setQuantity(quantity);
			cart.getItems().add(newItem);
			cartItemRepository.save(newItem);
		}

		Cart updated = cartRepository.save(cart);
		return toDto(updated);
	}

	@Override
	@Transactional
	public CartDto removeProductFromCart(Long userId, Long productId) {
		Cart cart = getOrCreateCart(userId);
		CartItem item = cart.getItems().stream().filter(i -> i.getProduct().getId().equals(productId)).findFirst()
				.orElseThrow(() -> new RuntimeException("Ürün sepette bulunamadı, id: " + productId));

		cart.getItems().remove(item);
		cartItemRepository.delete(item);

		Cart updated = cartRepository.save(cart);
		return toDto(updated);
	}

	@Override
	@Transactional
	public void clearCart(Long userId) {
		Cart cart = getOrCreateCart(userId);
		cartItemRepository.deleteAll(cart.getItems());
		cart.getItems().clear();
		cartRepository.save(cart);

	}

	private Cart getOrCreateCart(Long userId) {
		return cartRepository.findByUser_Id(userId).orElseGet(() -> {
					User user = userRepository.findById(userId)
							.orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı, id: " + userId));
					Cart newCart = new Cart();
					newCart.setUser(user);
					return cartRepository.save(newCart);
				});
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

	private CartItemDto toDto(CartItem item) {
		CartItemDto dto = new CartItemDto();
		dto.setId(item.getId());
		dto.setCartid(item.getCart().getId());
		dto.setProductId(item.getProduct().getId());
		dto.setQuantity(item.getQuantity());
		return dto;
	}

}
