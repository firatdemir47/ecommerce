package com.firatdemir.service.ımpl;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.firatdemir.dto.CarItemDto;
import com.firatdemir.dto.CartDto;
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
	public CartDto removeProductFromCart(Long userId, Long productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearCart(Long userId) {
		// TODO Auto-generated method stub

	}

	private Cart getOrCreateCart(Long userId) {
		return cartRepository.findAll().stream().filter(c -> c.getUser().getId().equals(userId)).findFirst()
				.orElseGet(() -> {
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

	private CarItemDto toDto(CartItem item) {
		CarItemDto dto = new CarItemDto();
		dto.setId(item.getId());
		dto.setCartid(item.getCart().getId());
		dto.setProductId(item.getProduct().getId());
		dto.setQuantity(item.getQuantity());
		return dto;
	}

}
