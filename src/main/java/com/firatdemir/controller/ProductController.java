package com.firatdemir.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.firatdemir.dto.ProductDto;
import com.firatdemir.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping
	public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {

		return ResponseEntity.ok(productService.creaateProduct(productDto));
	}

	@GetMapping
	public ResponseEntity<List<ProductDto>> getAllProducts() {
		return ResponseEntity.ok(productService.getAllProducts());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
		return ResponseEntity.ok(productService.getProductById(id));
	}
}
