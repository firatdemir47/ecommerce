package com.firatdemir.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.firatdemir.dto.ProductDto;

public interface ProductService {

	ProductDto creaateProduct(ProductDto productdto);

	ProductDto getProductById(Long id);

	List<ProductDto> getAllProducts();

	Page<ProductDto> getAllProducts(Pageable pageable);

	ProductDto updateProduct(Long id, ProductDto productDto);

	void deleteProduct(Long id);
}
