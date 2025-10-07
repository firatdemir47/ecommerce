package com.firatdemir.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {
	
	private Long id;
	
	private Long cartid;
	
	private Long productId;
	
	private Integer quantity;
}
