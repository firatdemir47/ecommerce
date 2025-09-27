package com.firatdemir.service;

import java.util.List;

import com.firatdemir.dto.ProductDto;

public interface ProductService {

	ProductDto creaateProduct(ProductDto productdto);

	ProductDto getProductById(Long id);

	List<ProductDto> getAllProducts();

	ProductDto updateProduct(Long id, ProductDto productDto);

	void deleteProduct(Long id);
}
