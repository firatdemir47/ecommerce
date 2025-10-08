 package com.firatdemir.service.ımpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.firatdemir.dto.ProductDto;
import com.firatdemir.exception.NotFoundException;
import com.firatdemir.model.Product;
import com.firatdemir.repository.ProductRepository;
import com.firatdemir.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
		
	}

	@Override
	public ProductDto creaateProduct(ProductDto productdto) {
		Product product = toEntiy(productdto);
		Product saved = productRepository.save(product);
		return toDto(saved);
	}

	@Override
	public ProductDto getProductById(Long id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Aranan Ürün Bulunamadı , id:" + id));
		return toDto(product);
	}

	@Override
	public List<ProductDto> getAllProducts() {

		return productRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
	}

	@Override
	public Page<ProductDto> getAllProducts(Pageable pageable) {
		return productRepository.findAll(pageable).map(this::toDto);
	}

	@Override
	public ProductDto updateProduct(Long id, ProductDto productDto) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Aranan Ürün Bulunamadı , id:" + id));
		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getPrice());
		product.setStock(productDto.getStock());
		product.setImageUrl(productDto.getImageUrl());

		Product updated = productRepository.save(product);

		return toDto(updated);
	}

	@Override
	public void deleteProduct(Long id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Aranan Ürün Bulunamadı , id:" + id));
		productRepository.delete(product);
	}

	private Product toEntiy(ProductDto dto) {
		Product product = new Product();
		product.setId(dto.getId());
		product.setName(dto.getName());
		product.setDescription(dto.getDescription());
		product.setPrice(dto.getPrice());
		product.setStock(dto.getStock());
		product.setImageUrl(dto.getImageUrl());
		
		return product;
	}

	private ProductDto toDto(Product entity) {
		ProductDto dto = new ProductDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setDescription(entity.getDescription());
		dto.setPrice(entity.getPrice());
		dto.setStock(entity.getStock());
		dto.setImageUrl(entity.getImageUrl());
		
		return dto;
	}

}
