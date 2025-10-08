package com.firatdemir.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
	
	private Long id;
	
	@NotBlank(message = "Ürün adı boş olamaz")
	@Size(max = 255, message = "Ürün adı 255 karakteri geçemez")
	private String name;
	
	@Size(max = 1000, message = "Açıklama 1000 karakteri geçemez")
	private String description;
	
	@NotNull(message = "Fiyat zorunludur")
	@PositiveOrZero(message = "Fiyat negatif olamaz")
	private Double price;
	
	@NotNull(message = "Stok zorunludur")
	@Min(value = 0, message = "Stok negatif olamaz")
	private Integer stock;
	
	private String imageUrl;
	
	private Long categoryId;
	
	
}
